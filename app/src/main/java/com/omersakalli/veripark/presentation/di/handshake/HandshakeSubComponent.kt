package com.omersakalli.veripark.presentation.di.handshake

import android.app.Application
import com.omersakalli.veripark.MainActivity
import dagger.Subcomponent
import javax.inject.Singleton

@HandshakeScope
@Subcomponent(modules = [HandshakeModule::class])
interface HandshakeSubComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():HandshakeSubComponent
    }
}