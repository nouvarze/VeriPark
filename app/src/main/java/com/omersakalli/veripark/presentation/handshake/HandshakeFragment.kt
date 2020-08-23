package com.omersakalli.veripark.presentation.handshake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.omersakalli.veripark.R
import com.omersakalli.veripark.databinding.HandshakeFragmentBinding

class HandshakeFragment : Fragment() {

    private lateinit var binding: HandshakeFragmentBinding
    private lateinit var viewModel: HandshakeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.handshake_fragment, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(requireActivity()).get(HandshakeViewModel::class.java)
        binding.goToListButton.setOnClickListener {
            val action =
                HandshakeFragmentDirections.actionHandshakeFragmentToİmkbListFragment("all")
            findNavController().navigate(action)
        }

    }




}