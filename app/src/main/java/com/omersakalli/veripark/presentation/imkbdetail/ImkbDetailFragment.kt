package com.omersakalli.veripark.presentation.imkbdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.omersakalli.veripark.R
import com.omersakalli.veripark.databinding.FragmentImkbDetailBinding


class ImkbDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentImkbDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_imkb_detail, container, false)
        val view: View = binding.root
        return view
    }

}