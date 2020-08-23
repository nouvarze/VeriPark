package com.omersakalli.veripark.presentation.di.core

import android.content.Context
import com.omersakalli.veripark.presentation.di.handshake.HandshakeSubComponent
import com.omersakalli.veripark.presentation.di.imkb.ImkbDetailSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [/*ImkbDetailSubComponent::class,ImkbListSubComponent::class,*/HandshakeSubComponent::class])
class AppModule(private val context : Context) {

    @Singleton
    @Provides
    fun provideApplicationContext():Context{
        return context.applicationContext
    }
}