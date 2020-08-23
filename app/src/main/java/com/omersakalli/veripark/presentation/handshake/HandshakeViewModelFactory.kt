package com.omersakalli.veripark.presentation.handshake

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omersakalli.veripark.domain.usecase.GetHandshakeUseCase
import com.omersakalli.veripark.domain.usecase.GetImkbDetailUseCase
import com.omersakalli.veripark.presentation.imkbdetail.ImkbDetailViewModel

class HandshakeViewModelFactory(private val getHandshakeUseCase: GetHandshakeUseCase):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HandshakeViewModel(getHandshakeUseCase) as T
    }
}