package com.omersakalli.veripark.data.repository.handshake.datasourceimpl

import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import com.omersakalli.veripark.data.repository.handshake.datasource.HandshakeCacheDataSource
import com.omersakalli.veripark.util.DateCheck

class HandshakeCacheDataSourceImpl : HandshakeCacheDataSource {
    private lateinit var handshakeResponse: HandshakeResponse

    /**
     * Returns handshakeresponse if it is still valid
     * if there weren't any or it isn't valid anymore returns null
     * so it can be requested from api
     */
    override suspend fun getHandshakeFromCache(): HandshakeResponse {
        if (handshakeResponse != null) {
            if (DateCheck.isLifetimeValid(handshakeResponse.lifeTime)) return handshakeResponse
        }
        return handshakeResponse
    }

    override suspend fun saveHandshakeToCache(response: HandshakeResponse) {
        handshakeResponse = response
    }

}