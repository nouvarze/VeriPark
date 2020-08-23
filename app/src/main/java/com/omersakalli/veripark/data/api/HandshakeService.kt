package com.omersakalli.veripark.data.api

import com.omersakalli.veripark.data.model.Handshake.HandshakeRequest
import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface HandshakeService {
    @POST("api/handshake/start")
    @Headers("Content-Type: application/json")
    suspend fun requestHandshake(
        @Body request: HandshakeRequest
    ) : Response<HandshakeResponse>
}