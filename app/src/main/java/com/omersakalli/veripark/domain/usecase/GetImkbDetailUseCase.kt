package com.omersakalli.veripark.domain.usecase

import com.omersakalli.veripark.data.model.IMKB.DetailResponse
import com.omersakalli.veripark.data.model.IMKB.Stock
import com.omersakalli.veripark.domain.repository.ImkbDetailRepository
import com.omersakalli.veripark.domain.repository.ImkbListRepository

class GetImkbDetailUseCase(private val imkbRepository: ImkbDetailRepository, private val aesEncryptedID:String) {
    suspend fun execute(): DetailResponse = imkbRepository.getImkbDetail(aesEncryptedID)

}