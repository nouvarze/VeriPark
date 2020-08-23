package com.omersakalli.veripark.data.repository.handshake.datasourceimpl

import com.omersakalli.veripark.data.api.HandshakeService
import com.omersakalli.veripark.data.api.ImkbService
import com.omersakalli.veripark.data.model.Handshake.HandshakeRequest
import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import com.omersakalli.veripark.data.repository.handshake.datasource.HandshakeRemoteDataSource
import retrofit2.Response

class HandshakeRemoteDataSourceImpl(
    private val handshakeService: HandshakeService,
    private val handshakeRequest: HandshakeRequest
) :
    HandshakeRemoteDataSource {
    override suspend fun getHandshake() : Response<HandshakeResponse> =
        handshakeService.requestHandshake(handshakeRequest)
}