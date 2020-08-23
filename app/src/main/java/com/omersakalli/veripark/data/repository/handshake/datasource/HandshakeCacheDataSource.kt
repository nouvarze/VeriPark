package com.omersakalli.veripark.data.repository.handshake.datasource

import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse

interface HandshakeCacheDataSource {

    suspend fun getHandshakeFromCache(): HandshakeResponse
    suspend fun saveHandshakeToCache(response:HandshakeResponse)
}