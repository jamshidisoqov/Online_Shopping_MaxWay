package uz.gita.online_shopping.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.online_shopping.domain.*
import uz.gita.online_shopping.domain.impl.*

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

    @Binds
    fun bindLoginUseCase(impl: LoginUseCaseImpl): LoginUseCase

    @Binds
    fun bindBranchUseCase(impl: BranchesUseCaseImpl): BranchesUseCase

}