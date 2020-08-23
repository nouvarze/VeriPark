package com.omersakalli.veripark.presentation.di.core

import com.omersakalli.veripark.data.repository.handshake.datasource.HandshakeCacheDataSource
import com.omersakalli.veripark.data.repository.handshake.datasourceimpl.HandshakeCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {
    @Singleton
    @Provides
    fun provideHandshakeCacheDataSource():HandshakeCacheDataSource{
        return HandshakeCacheDataSourceImpl()
    }
}