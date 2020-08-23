package com.omersakalli.veripark.presentation.di.core

import com.omersakalli.veripark.data.api.HandshakeService
import com.omersakalli.veripark.data.model.Handshake.HandshakeRequest
import com.omersakalli.veripark.data.repository.handshake.datasource.HandshakeRemoteDataSource
import com.omersakalli.veripark.data.repository.handshake.datasourceimpl.HandshakeRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class HandshakeRemoteDataModule(private val handshakeRequest: HandshakeRequest) {
    @Singleton
    @Provides
    fun provideHandshakeRemoteDataSource(handshakeService: HandshakeService):HandshakeRemoteDataSource{
        return HandshakeRemoteDataSourceImpl(handshakeService, handshakeRequest)
    }
}