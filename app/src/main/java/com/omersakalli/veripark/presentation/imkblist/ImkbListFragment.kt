package com.omersakalli.veripark.presentation.imkblist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.omersakalli.veripark.R
import com.omersakalli.veripark.data.model.IMKB.ListRequest
import com.omersakalli.veripark.databinding.FragmentImkbListBinding
import com.omersakalli.veripark.presentation.di.core.NetModule
import com.omersakalli.veripark.presentation.handshake.HandshakeViewModel
import com.omersakalli.veripark.util.AES_Functions

class ImkbListFragment : Fragment() {

    private lateinit var binding: FragmentImkbListBinding
    private lateinit var adapter: ListAdapter
    private lateinit var imkbListViewModel: ImkbListViewModel
    private val handshakeViewModel: HandshakeViewModel by activityViewModels()
    private lateinit var stockPeriod: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_imkb_list, container, false)

        imkbListViewModel = ViewModelProvider(this).get(ImkbListViewModel::class.java)
        val imkbService = NetModule().provideImkbService()

        stockPeriod = requireArguments().getString("period", "all")

        initRecyclerView()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ImkbListFragment().apply {}
    }

    private fun initRecyclerView() {
        binding.listRecyclerview.layoutManager = LinearLayoutManager(context)
        adapter = ListAdapter()
        binding.listRecyclerview.adapter = adapter
        displayStocks(stockPeriod)
    }

    /**
     * Gets imkb stocks from api using handshake data and feeds it to list adapter
     * @param stockPeriod - Listing type of stocks can be one of these (as listed in api documentation in Turkish)
     * 	|---> all: IMKB hisse ve endeksleri almak için kullanılır.
     * 	|---> increasing: Yükselenleri almak için kullanılır.
     * 	|---> decreasing: Düşenleri almak için kullanılır.
     * 	|---> volume30: IMKB hacme göre 30 listesini almak için kullanılır.
     * 	|---> volume50: IMKB hacme göre 50 listesini almak için kullanılır.
     * 	\---> volume100: IMKB hacme göre 100 listesini almak için kullanılır.
     */
    private fun displayStocks(stockPeriod: String) {
        binding.listProgressBar.visibility = View.VISIBLE
        handshakeViewModel.getHandshake().observe(viewLifecycleOwner, Observer { handshake ->
            imkbListViewModel.getImkbList(
                handshake.authorization,
                ListRequest(AES_Functions.encrypt(stockPeriod, handshake.aesKey, handshake.aesIV))
            ).observe(viewLifecycleOwner,
                Observer { imkb ->
                    if (imkb.body() != null && !imkb.body()!!.stocks.isEmpty()) {
                        adapter.setList(
                            imkb.body()!!.stocks,
                            handshake.aesKey,
                            handshake.aesIV
                        )
                        adapter.notifyDataSetChanged()
                        binding.listProgressBar.visibility = View.GONE
                    } else {
                        binding.listProgressBar.visibility = View.GONE
                        Toast.makeText(context, "No data available", Toast.LENGTH_LONG).show()
                    }
                })
        })
    }
}