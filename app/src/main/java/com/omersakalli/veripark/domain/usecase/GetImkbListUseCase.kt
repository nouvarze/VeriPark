package com.omersakalli.veripark.domain.usecase

import com.omersakalli.veripark.data.model.IMKB.ListResponse
import com.omersakalli.veripark.data.model.IMKB.Stock
import com.omersakalli.veripark.domain.repository.ImkbListRepository

class GetImkbListUseCase(private val imkbRepository:ImkbListRepository) {
    suspend fun execute():ListResponse = imkbRepository.getImkbList()
}