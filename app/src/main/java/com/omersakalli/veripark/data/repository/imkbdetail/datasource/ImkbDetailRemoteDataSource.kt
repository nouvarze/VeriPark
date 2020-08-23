package com.omersakalli.veripark.data.repository.imkblist.datasource

import com.omersakalli.veripark.data.model.IMKB.DetailResponse
import com.omersakalli.veripark.data.model.IMKB.ListResponse
import retrofit2.Response

interface ImkbDetailRemoteDataSource {
    suspend fun getImkbDetail(): Response<DetailResponse>
}