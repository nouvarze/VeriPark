package com.omersakalli.veripark.domain.repository

import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse

interface HandshakeRepository {

    suspend fun getHandshake():HandshakeResponse
}