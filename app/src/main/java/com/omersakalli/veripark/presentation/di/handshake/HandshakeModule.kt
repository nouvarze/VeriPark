package com.omersakalli.veripark.presentation.di.handshake

import com.omersakalli.veripark.domain.usecase.GetHandshakeUseCase
import com.omersakalli.veripark.presentation.handshake.HandshakeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HandshakeModule {
    @HandshakeScope
    @Provides
    fun provideHandshake(getHandshakeUseCase: GetHandshakeUseCase): HandshakeViewModelFactory {
        return HandshakeViewModelFactory(getHandshakeUseCase)
    }
}