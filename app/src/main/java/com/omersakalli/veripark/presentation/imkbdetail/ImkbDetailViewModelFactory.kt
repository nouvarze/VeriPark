package com.omersakalli.veripark.presentation.imkbdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.omersakalli.veripark.domain.usecase.GetImkbDetailUseCase

class ImkbDetailViewModelFactory(private val getImkbDetailUseCase: GetImkbDetailUseCase):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImkbDetailViewModel(getImkbDetailUseCase) as T
    }
}