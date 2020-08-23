package com.omersakalli.veripark.domain.usecase

import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import com.omersakalli.veripark.domain.repository.HandshakeRepository

class GetHandshakeUseCase (private val handshakeRepository: HandshakeRepository) {
    suspend fun execute() : HandshakeResponse = handshakeRepository.getHandshake()
}