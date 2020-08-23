package com.omersakalli.veripark.data.repository.handshake.datasource

import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import retrofit2.Response

interface HandshakeRemoteDataSource {
    suspend fun getHandshake(): Response<HandshakeResponse>
}