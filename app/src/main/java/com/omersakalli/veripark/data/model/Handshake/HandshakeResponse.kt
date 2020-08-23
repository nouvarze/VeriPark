package com.omersakalli.veripark.data.model.Handshake


import com.google.gson.annotations.SerializedName
import com.omersakalli.veripark.data.model.Status

data class HandshakeResponse(
    @SerializedName("aesIV")
    val aesIV: String,
    @SerializedName("aesKey")
    val aesKey: String,
    @SerializedName("authorization")
    val authorization: String,
    @SerializedName("lifeTime")
    val lifeTime: String,
    @SerializedName("status")
    val status: Status
)