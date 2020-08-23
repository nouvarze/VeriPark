package com.omersakalli.veripark.presentation.handshake

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.omersakalli.veripark.domain.usecase.GetHandshakeUseCase

class HandshakeViewModel(private val handshakeUseCase: GetHandshakeUseCase) : ViewModel(){

    fun getHandshake() = liveData {
        val handshake = handshakeUseCase.execute()
        Log.i("MYTAG",handshake.lifeTime.toString())

        emit(handshake)
    }


}