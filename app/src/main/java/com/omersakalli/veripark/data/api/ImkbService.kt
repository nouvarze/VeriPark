package com.omersakalli.veripark.data.api


import com.omersakalli.veripark.data.model.Handshake.HandshakeRequest
import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import com.omersakalli.veripark.data.model.IMKB.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ImkbService {

    @POST("api/stocks/list")
    @Headers("Content-Type: application/json")
    suspend fun requestList(
        @Header("X-VP-Authorization") XVPAuthorization:String,
        @Body request: ListRequest
    ) : Response<ListResponse>

    @POST("api/stocks/detail")
    @Headers("Content-Type: application/json")
    suspend fun requestDetail(
        @Header("X-VP-Authorization") XVPAuthorization:String,
        @Body request: DetailRequest
    ) : Response<DetailResponse>

}