package com.omersakalli.veripark.presentation.imkblist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.omersakalli.veripark.R
import com.omersakalli.veripark.data.api.ImkbService
import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import com.omersakalli.veripark.data.model.IMKB.ListRequest
import com.omersakalli.veripark.data.repository.imkblist.ImkbListRepositoryImpl
import com.omersakalli.veripark.data.repository.imkblist.datasourceimpl.ImkbListRemoteDataSourceImpl
import com.omersakalli.veripark.databinding.FragmentImkbListBinding
import com.omersakalli.veripark.databinding.HandshakeFragmentBinding
import com.omersakalli.veripark.domain.repository.ImkbListRepository
import com.omersakalli.veripark.domain.usecase.GetImkbListUseCase
import com.omersakalli.veripark.presentation.di.Injector
import com.omersakalli.veripark.presentation.di.core.NetModule
import com.omersakalli.veripark.presentation.handshake.HandshakeViewModel
import com.omersakalli.veripark.util.AES_Functions
import retrofit2.Retrofit
import javax.inject.Inject

class ImkbListFragment : Fragment() {

    //lateinit var factory: ImkbListViewModelFactory
    private lateinit var binding: FragmentImkbListBinding
    private lateinit var adapter: ListAdapter
    private lateinit var imkbListViewModel: ImkbListViewModel
    private val handshakeViewModel: HandshakeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_imkb_list, container, false)

//        (requireActivity().application as Injector).createImkbListSubComponent().inject(this)
        imkbListViewModel = ViewModelProvider(this).get(ImkbListViewModel::class.java)
        val imkbService = NetModule().provideImkbService()
        handshakeViewModel.getHandshake().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                imkbListViewModel.getImkbListUseCase =
                    GetImkbListUseCase(
                        ImkbListRepositoryImpl(
                            ImkbListRemoteDataSourceImpl(
                                imkbService,
                                it,
                                ListRequest("all")
                            )
                        )
                    )
                initRecyclerView()
            }
        })





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
        displayStocks()
    }

    private fun displayStocks() {
        binding.listProgressBar.visibility = View.VISIBLE
        handshakeViewModel.getHandshake().observe(viewLifecycleOwner, Observer {handshake->
            imkbListViewModel.getImkbList(handshake.authorization,ListRequest(AES_Functions.encrypt("all",handshake.aesKey,handshake.aesIV))).observe(viewLifecycleOwner,
                Observer { imkb->
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
        })/*
        val responseLiveData = imkbListViewModel.getImkbList()
        responseLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null && !it.stocks.isEmpty()) {
                adapter.setList(
                    it.stocks,
                    handshakeViewModel.getHandshake().value!!.aesKey,
                    handshakeViewModel.getHandshake().value!!.aesKey
                )
                adapter.notifyDataSetChanged()
                binding.listProgressBar.visibility = View.GONE
            } else {
                binding.listProgressBar.visibility = View.GONE
                Toast.makeText(context, "No data available", Toast.LENGTH_LONG).show()
            }
        })*/
    }
}