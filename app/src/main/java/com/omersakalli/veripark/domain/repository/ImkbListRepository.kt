package com.omersakalli.veripark.domain.repository

import com.omersakalli.veripark.data.model.IMKB.DetailResponse
import com.omersakalli.veripark.data.model.IMKB.ListResponse
import com.omersakalli.veripark.data.model.IMKB.Stock

interface ImkbListRepository {
    suspend fun getImkbList():ListResponse
}