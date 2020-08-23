package com.omersakalli.veripark.data.model.Handshake


import com.google.gson.annotations.SerializedName

data class HandshakeRequest(
    @SerializedName("deviceId")
    val deviceId: String,
    @SerializedName("deviceModel")
    val deviceModel: String,
    @SerializedName("manifacturer")
    val manifacturer: String,
    @SerializedName("platformName")
    val platformName: String,
    @SerializedName("systemVersion")
    val systemVersion: String
)