package com.omersakalli.veripark.presentation.di.core

import com.omersakalli.veripark.data.api.HandshakeService
import com.omersakalli.veripark.data.model.Handshake.HandshakeRequest
import com.omersakalli.veripark.data.repository.handshake.datasource.HandshakeRemoteDataSource
import com.omersakalli.veripark.data.repository.handshake.datasourceimpl.HandshakeRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val handshakeRequest: HandshakeRequest) {


}