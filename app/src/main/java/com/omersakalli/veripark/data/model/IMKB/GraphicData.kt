package com.omersakalli.veripark.data.model.IMKB


import com.google.gson.annotations.SerializedName

data class GraphicData(
    @SerializedName("day")
    val day: Int,
    @SerializedName("value")
    val value: Double
)