package com.omersakalli.veripark.data.model.IMKB


import com.google.gson.annotations.SerializedName
import com.omersakalli.veripark.data.model.Status

data class ListResponse(
    @SerializedName("status")
    val status: Status,
    @SerializedName("stocks")
    val stocks: List<Stock>
)