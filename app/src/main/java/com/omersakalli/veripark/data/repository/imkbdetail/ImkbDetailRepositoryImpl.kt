package com.omersakalli.veripark.data.repository.imkbdetail

import android.util.Log
import com.omersakalli.veripark.data.model.IMKB.DetailResponse

import com.omersakalli.veripark.data.model.IMKB.ListResponse
import com.omersakalli.veripark.data.repository.imkblist.datasource.ImkbDetailRemoteDataSource
import com.omersakalli.veripark.domain.repository.ImkbDetailRepository
import com.omersakalli.veripark.domain.repository.ImkbListRepository
import java.lang.Exception

class ImkbDetailRepositoryImpl(private val imkbRemoteDataSource: ImkbDetailRemoteDataSource) :
    ImkbDetailRepository {

    override suspend fun getImkbDetail(aesEncryptedID: String): DetailResponse {
        return getImkbDetailFromAPI()
    }

    suspend fun getImkbDetailFromAPI(): DetailResponse {
        lateinit var response: DetailResponse
        try {
            val body = imkbRemoteDataSource.getImkbDetail().body()
            if (body != null) response = body
        } catch (exception: Exception) {
            Log.e("AppError", exception.message.toString())
        }
        return response
    }
}