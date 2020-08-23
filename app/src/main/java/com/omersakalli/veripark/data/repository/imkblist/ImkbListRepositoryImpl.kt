package com.omersakalli.veripark.data.repository.imkblist

import android.util.Log
import com.omersakalli.veripark.data.model.Error
import com.omersakalli.veripark.data.model.IMKB.DetailResponse

import com.omersakalli.veripark.data.model.IMKB.ListResponse
import com.omersakalli.veripark.data.model.IMKB.Stock
import com.omersakalli.veripark.data.model.Status
import com.omersakalli.veripark.data.repository.imkblist.datasource.ImkbDetailRemoteDataSource
import com.omersakalli.veripark.data.repository.imkblist.datasource.ImkbListRemoteDataSource
import com.omersakalli.veripark.domain.repository.ImkbListRepository
import java.lang.Exception

class ImkbListRepositoryImpl(private val imkbListRemoteDataSource: ImkbListRemoteDataSource) :
    ImkbListRepository {

    override suspend fun getImkbList(): ListResponse {
        return getImkbListFromAPI()
    }

    suspend fun getImkbListFromAPI(): ListResponse {
        lateinit var response: ListResponse
        response = ListResponse(Status(com.omersakalli.veripark.data.model.Error(0,""),false), emptyList())
        try {
            val body = imkbListRemoteDataSource.getImkbList().body()
            if (body != null) response = body
        } catch (exception: Exception) {
            Log.e("AppError", exception.message.toString())
        }
        return response
    }
}