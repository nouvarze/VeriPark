package com.omersakalli.veripark.presentation.di.core


import com.omersakalli.veripark.presentation.di.handshake.HandshakeSubComponent
import com.omersakalli.veripark.presentation.di.imkb.ImkbDetailSubComponent
//import com.omersakalli.veripark.presentation.di.imkb.ImkbListSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        //ImkbListRemoteDataModule::class,
        //ImkbDetailRemoteDataModule::class,
        HandshakeRemoteDataModule::class,
        CacheDataModule::class,
    ]
)
interface AppComponent {
    //fun imkbListSubComponent():ImkbListSubComponent.Factory
    //fun imkbDetailSubComponent(): ImkbDetailSubComponent.Factory
    fun handshakeSubComponent():HandshakeSubComponent.Factory
}