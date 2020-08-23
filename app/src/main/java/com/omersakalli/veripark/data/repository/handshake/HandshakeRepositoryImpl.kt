package com.omersakalli.veripark.data.repository.handshake

import android.util.Log
import com.omersakalli.veripark.data.api.ImkbService
import com.omersakalli.veripark.data.model.Error
import com.omersakalli.veripark.data.model.Handshake.HandshakeRequest
import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import com.omersakalli.veripark.data.model.Status
import com.omersakalli.veripark.data.repository.handshake.datasource.HandshakeCacheDataSource
import com.omersakalli.veripark.data.repository.handshake.datasource.HandshakeRemoteDataSource
import com.omersakalli.veripark.domain.repository.HandshakeRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.lang.Exception


class HandshakeRepositoryImpl(
    private val handshakeRemoteDataSource: HandshakeRemoteDataSource,
    private val handshakeCacheDataSource: HandshakeCacheDataSource
) : HandshakeRepository {

    override suspend fun getHandshake(): HandshakeResponse {
        return getHandshakeFromCache()
    }

    suspend fun getHandshakeFromAPI(): HandshakeResponse {
        lateinit var response: HandshakeResponse
        try {
            val body = handshakeRemoteDataSource.getHandshake().body()
            if (body != null) response = body
        } catch (exception: Exception) {
            Log.e("AppError", exception.message.toString())
        }
        return response
    }

    suspend fun getHandshakeFromCache(): HandshakeResponse {
        lateinit var response: HandshakeResponse
        try {
            response = handshakeCacheDataSource.getHandshakeFromCache()
        } catch (exception: Exception) {
            Log.e("AppError", exception.message.toString())
        }
        if (true) {
            response = getHandshakeFromAPI()
            handshakeCacheDataSource.saveHandshakeToCache(response)
        }
        return response
    }

}