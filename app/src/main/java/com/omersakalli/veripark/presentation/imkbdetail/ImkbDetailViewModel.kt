package com.omersakalli.veripark.presentation.imkbdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.omersakalli.veripark.domain.usecase.GetImkbDetailUseCase

class ImkbDetailViewModel(private val getImkbDetailUseCase: GetImkbDetailUseCase) : ViewModel() {

    fun getImkbDetail() = liveData {
        val imkbDetail = getImkbDetailUseCase.execute()
        emit(imkbDetail)
    }

}