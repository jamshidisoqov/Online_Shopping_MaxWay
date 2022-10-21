package uz.gita.online_shopping.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.online_shopping.domain.MainUseCase
import uz.gita.online_shopping.domain.OrderUseCase
import uz.gita.online_shopping.domain.ProfileUseCase
import uz.gita.online_shopping.domain.impl.MainUseCaseImpl
import uz.gita.online_shopping.domain.impl.OrderUseCaseImpl
import uz.gita.online_shopping.domain.impl.ProfileUseCaseImpl

// Created by Jamshid Isoqov an 10/9/2022
@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindMainUseCase(impl: MainUseCaseImpl): MainUseCase

    @Binds
    fun bindOrderUseCase(impl: OrderUseCaseImpl): OrderUseCase

    @Binds
    fun bindProfileUseCase(impl: ProfileUseCaseImpl): ProfileUseCase
}