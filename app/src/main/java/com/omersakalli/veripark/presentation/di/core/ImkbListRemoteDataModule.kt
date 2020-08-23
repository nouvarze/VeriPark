package com.omersakalli.veripark.presentation.di.core

import com.omersakalli.veripark.data.api.ImkbService
import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import com.omersakalli.veripark.data.model.IMKB.ListRequest
import com.omersakalli.veripark.data.repository.imkblist.datasource.ImkbDetailRemoteDataSource
import com.omersakalli.veripark.data.repository.imkblist.datasource.ImkbListRemoteDataSource
import com.omersakalli.veripark.data.repository.imkblist.datasourceimpl.ImkbDetailRemoteDataSourceImpl
import com.omersakalli.veripark.data.repository.imkblist.datasourceimpl.ImkbListRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImkbListRemoteDataModule(
    private val handshakeResponse: HandshakeResponse,
    private val listRequest: ListRequest
) {
    @Singleton
    @Provides
    fun provideImkbListRemoteDataSource(imkbService: ImkbService): ImkbListRemoteDataSource {
        return ImkbListRemoteDataSourceImpl(
            imkbService, handshakeResponse, listRequest
        )
    }
}