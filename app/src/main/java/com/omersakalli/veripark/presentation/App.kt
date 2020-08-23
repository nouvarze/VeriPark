package com.omersakalli.veripark.presentation

import android.app.Application
import android.os.Build
import android.provider.Settings
import com.omersakalli.veripark.data.model.Handshake.HandshakeRequest
import com.omersakalli.veripark.presentation.di.Injector
import com.omersakalli.veripark.presentation.di.core.*
import com.omersakalli.veripark.presentation.di.handshake.HandshakeSubComponent
import com.omersakalli.veripark.presentation.di.imkb.ImkbDetailSubComponent
//import com.omersakalli.veripark.presentation.di.imkb.ImkbListSubComponent

class App : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule())
            .handshakeRemoteDataModule(
                HandshakeRemoteDataModule(
                    HandshakeRequest(
                        Settings.Secure.getString(
                            applicationContext.contentResolver,
                            Settings.Secure.ANDROID_ID
                        ),
                        Build.MODEL,
                        Build.MANUFACTURER,
                        if (Build.FINGERPRINT.contains("generic")) {
                            "AndroidSimulator"
                        } else {
                            "Android"
                        },
                        Build.VERSION.SDK_INT.toString()
                    )
                )
            )
            .build()

    }
/*
    override fun createImkbDetailSubComponent(): ImkbDetailSubComponent {
        return  appComponent.imkbDetailSubComponent().create()
    }

    override fun createImkbListSubComponent(): ImkbListSubComponent {
        return appComponent.imkbListSubComponent().create()
    }
*/
    override fun createHandshakeSubComponent(): HandshakeSubComponent {
        return appComponent.handshakeSubComponent().create()
    }


}