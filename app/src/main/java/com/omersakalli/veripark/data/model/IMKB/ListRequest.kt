package com.omersakalli.veripark.data.model.IMKB


import com.google.gson.annotations.SerializedName

data class ListRequest(
    @SerializedName("period")
    val period: String
)