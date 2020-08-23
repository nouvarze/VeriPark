package com.omersakalli.veripark.data.model


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("error")
    val error: Error,
    @SerializedName("isSuccess")
    val isSuccess: Boolean
)