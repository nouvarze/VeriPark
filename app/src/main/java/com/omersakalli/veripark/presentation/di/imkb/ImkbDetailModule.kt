package com.omersakalli.veripark.presentation.di.imkb

import com.omersakalli.veripark.domain.usecase.GetImkbDetailUseCase
import com.omersakalli.veripark.domain.usecase.GetImkbListUseCase
import com.omersakalli.veripark.presentation.imkbdetail.ImkbDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ImkbDetailModule {
    @ImkbDetailScope
    @Provides
    fun provideImkbDetailViewModelFactory(getImkbDetailUseCase: GetImkbDetailUseCase):ImkbDetailViewModelFactory{
        return ImkbDetailViewModelFactory(getImkbDetailUseCase)
    }
}