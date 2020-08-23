package com.omersakalli.veripark.data.repository.imkblist.datasourceimpl

import com.omersakalli.veripark.data.api.ImkbService
import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import com.omersakalli.veripark.data.model.IMKB.DetailRequest
import com.omersakalli.veripark.data.model.IMKB.DetailResponse
import com.omersakalli.veripark.data.model.IMKB.ListRequest
import com.omersakalli.veripark.data.model.IMKB.ListResponse
import com.omersakalli.veripark.data.repository.imkblist.datasource.ImkbDetailRemoteDataSource
import retrofit2.Response

class ImkbDetailRemoteDataSourceImpl(
    private val imkbService: ImkbService,
    private val handshakeResponse: HandshakeResponse,
    private val detailRequest: DetailRequest
) : ImkbDetailRemoteDataSource {

    override suspend fun getImkbDetail(): Response<DetailResponse> = imkbService.requestDetail(handshakeResponse.authorization,detailRequest)
}