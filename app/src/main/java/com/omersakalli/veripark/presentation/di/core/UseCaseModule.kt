package com.omersakalli.veripark.presentation.di.core

import com.omersakalli.veripark.data.model.Handshake.HandshakeResponse
import com.omersakalli.veripark.domain.repository.HandshakeRepository
import com.omersakalli.veripark.domain.repository.ImkbDetailRepository
import com.omersakalli.veripark.domain.repository.ImkbListRepository
import com.omersakalli.veripark.domain.usecase.GetHandshakeUseCase
import com.omersakalli.veripark.domain.usecase.GetImkbDetailUseCase
import com.omersakalli.veripark.domain.usecase.GetImkbListUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideGetHandshakeUseCase(handshakeRepository: HandshakeRepository):GetHandshakeUseCase{
        return GetHandshakeUseCase(handshakeRepository)
    }
    @Provides
    fun provideGetImkbListUseCase(imkbRepository: ImkbListRepository):GetImkbListUseCase{
        return GetImkbListUseCase(imkbRepository)
    }
    @Provides
    fun provideGetImkbDetailUseCase(imkbRepository: ImkbDetailRepository, aesEncryptedPeriod:String): GetImkbDetailUseCase {
        return GetImkbDetailUseCase(imkbRepository,aesEncryptedPeriod)
    }
}