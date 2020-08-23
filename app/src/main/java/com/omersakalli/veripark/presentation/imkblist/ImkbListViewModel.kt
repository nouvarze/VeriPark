package com.omersakalli.veripark.presentation.imkblist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.omersakalli.veripark.data.model.IMKB.ListRequest
import com.omersakalli.veripark.data.model.IMKB.ListResponse
import com.omersakalli.veripark.domain.usecase.GetHandshakeUseCase
import com.omersakalli.veripark.domain.usecase.GetImkbListUseCase
import com.omersakalli.veripark.presentation.di.core.NetModule
import retrofit2.Response

class ImkbListViewModel() : ViewModel() {
    lateinit var getImkbListUseCase: GetImkbListUseCase

    fun getImkbList(auth:String,request: ListRequest) = liveData {
        val imkbList:Response<ListResponse> = NetModule().provideImkbService().requestList(auth,request)
        emit(imkbList)
    }


}