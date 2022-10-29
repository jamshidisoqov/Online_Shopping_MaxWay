package uz.gita.online_shopping.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.online_shopping.repository.MaxWayRepository
import uz.gita.online_shopping.repository.impl.MaxWayRepositoryImpl
import javax.inject.Singleton

// Created by Jamshid Isoqov an 10/9/2022
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindMaxWayRepository(impl: MaxWayRepositoryImpl): MaxWayRepository

}