package com.omersakalli.veripark.presentation.di.core

import com.omersakalli.veripark.data.api.ImkbService
import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import com.omersakalli.veripark.data.model.IMKB.DetailRequest
import com.omersakalli.veripark.data.model.IMKB.ListRequest
import com.omersakalli.veripark.data.repository.imkblist.datasource.ImkbDetailRemoteDataSource
import com.omersakalli.veripark.data.repository.imkblist.datasource.ImkbListRemoteDataSource
import com.omersakalli.veripark.data.repository.imkblist.datasourceimpl.ImkbDetailRemoteDataSourceImpl
import com.omersakalli.veripark.data.repository.imkblist.datasourceimpl.ImkbListRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImkbDetailRemoteDataModule(private val handshakeResponse: HandshakeResponse, private val detailRequest: DetailRequest) {
    @Singleton
    @Provides
    fun provideImkbDetailRemoteDataSource(imkbService: ImkbService): ImkbDetailRemoteDataSource {
        return ImkbDetailRemoteDataSourceImpl(
            imkbService,handshakeResponse,detailRequest
        )
    }
}