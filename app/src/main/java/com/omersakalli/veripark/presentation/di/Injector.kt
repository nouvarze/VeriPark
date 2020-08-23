package com.omersakalli.veripark.presentation.di

import com.omersakalli.veripark.presentation.di.handshake.HandshakeSubComponent
import com.omersakalli.veripark.presentation.di.imkb.ImkbDetailSubComponent
//import com.omersakalli.veripark.presentation.di.imkb.ImkbListSubComponent

interface Injector {
//    fun createImkbDetailSubComponent():ImkbDetailSubComponent
//    fun createImkbListSubComponent(): ImkbListSubComponent
    fun createHandshakeSubComponent(): HandshakeSubComponent

}