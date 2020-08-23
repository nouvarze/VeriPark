package com.omersakalli.veripark.presentation.di.imkb

import com.omersakalli.veripark.presentation.imkbdetail.ImkbDetailFragment
import dagger.Subcomponent

@ImkbListScope
@Subcomponent(modules = [ImkbDetailModule::class])
interface ImkbDetailSubComponent {

    fun inject(imkbDetailFragment: ImkbDetailFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create():ImkbDetailSubComponent
    }
}