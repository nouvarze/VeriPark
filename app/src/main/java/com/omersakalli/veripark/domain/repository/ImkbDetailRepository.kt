package com.omersakalli.veripark.domain.repository

import com.omersakalli.veripark.data.model.IMKB.DetailResponse
import com.omersakalli.veripark.data.model.IMKB.ListResponse
import com.omersakalli.veripark.data.model.IMKB.Stock

interface ImkbDetailRepository {
    suspend fun getImkbDetail(aesEncryptedID:String):DetailResponse
}