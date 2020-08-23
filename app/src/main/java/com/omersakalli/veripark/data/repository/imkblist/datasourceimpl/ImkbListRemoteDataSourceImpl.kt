package com.omersakalli.veripark.data.repository.imkblist.datasourceimpl

import com.omersakalli.veripark.data.api.ImkbService
import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import com.omersakalli.veripark.data.model.IMKB.DetailResponse
import com.omersakalli.veripark.data.model.IMKB.ListRequest
import com.omersakalli.veripark.data.model.IMKB.ListResponse
import com.omersakalli.veripark.data.repository.imkblist.datasource.ImkbDetailRemoteDataSource
import com.omersakalli.veripark.data.repository.imkblist.datasource.ImkbListRemoteDataSource
import retrofit2.Response

class ImkbListRemoteDataSourceImpl(
    private val imkbService: ImkbService,
    private val handshakeResponse: HandshakeResponse,
    private val listRequest: ListRequest
) : ImkbListRemoteDataSource {
    override suspend fun getImkbList(): Response<ListResponse> = imkbService.requestList(handshakeResponse.authorization,listRequest)

}