package com.omersakalli.veripark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.omersakalli.veripark.databinding.MainActivityBinding
import com.omersakalli.veripark.presentation.di.Injector
import com.omersakalli.veripark.presentation.handshake.HandshakeViewModel
import com.omersakalli.veripark.presentation.handshake.HandshakeViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: HandshakeViewModelFactory
    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: HandshakeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.main_activity)
        (application as Injector).createHandshakeSubComponent().inject(this)

        viewModel=ViewModelProvider(this,factory).get(HandshakeViewModel::class.java)

        val a=viewModel.getHandshake()

        a.observe(this, Observer {
            Log.i("MYTAG","\n\nAES KEY: ${it.aesKey}\nAES IV: ${it.aesIV}\nAuth: ${it.authorization}\nlifetime ${it.lifeTime}")
        })

    }


}