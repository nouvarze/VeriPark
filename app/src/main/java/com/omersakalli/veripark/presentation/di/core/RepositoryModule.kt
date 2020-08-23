package com.omersakalli.veripark.presentation.di.core

import com.omersakalli.veripark.data.repository.handshake.HandshakeRepositoryImpl
import com.omersakalli.veripark.data.repository.handshake.datasource.HandshakeCacheDataSource
import com.omersakalli.veripark.data.repository.handshake.datasource.HandshakeRemoteDataSource
import com.omersakalli.veripark.data.repository.imkbdetail.ImkbDetailRepositoryImpl
import com.omersakalli.veripark.data.repository.imkblist.ImkbListRepositoryImpl
import com.omersakalli.veripark.data.repository.imkblist.datasource.ImkbDetailRemoteDataSource
import com.omersakalli.veripark.data.repository.imkblist.datasource.ImkbListRemoteDataSource
import com.omersakalli.veripark.domain.repository.HandshakeRepository
import com.omersakalli.veripark.domain.repository.ImkbDetailRepository
import com.omersakalli.veripark.domain.repository.ImkbListRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideHandshakeRepository(
        handshakeRemoteDataSource:HandshakeRemoteDataSource,
        handshakeCacheDataSource: HandshakeCacheDataSource
    ):HandshakeRepository{
        return HandshakeRepositoryImpl(
            handshakeRemoteDataSource,
            handshakeCacheDataSource
        )
    }

    @Provides
    @Singleton
    fun provideImkbListRepository(
        ImkbRemoteDataSource:ImkbListRemoteDataSource
    ):ImkbListRepository{
        return ImkbListRepositoryImpl(
            ImkbRemoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideImkbDetailRepository(
        ImkbRemoteDataSource:ImkbDetailRemoteDataSource
    ): ImkbDetailRepository {
        return ImkbDetailRepositoryImpl(
            ImkbRemoteDataSource
        )
    }
}