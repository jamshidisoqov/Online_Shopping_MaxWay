package uz.gita.online_shopping.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.online_shopping.directions.BasketScreenDirections
import uz.gita.online_shopping.directions.MainScreenDirection
import uz.gita.online_shopping.directions.OrderScreenDirection
import uz.gita.online_shopping.directions.ProfileScreenDirections
import uz.gita.online_shopping.directions.impl.BasketScreenDirectionsImpl
import uz.gita.online_shopping.directions.impl.MainScreenDirectionImpl
import uz.gita.online_shopping.directions.impl.OrderScreenDirectionImpl
import uz.gita.online_shopping.directions.impl.ProfileScreenDirectionsImpl

// Created by Jamshid Isoqov an 10/12/2022
@Module
@InstallIn(ViewModelComponent::class)
interface DirectionsModule {

    @Binds
    fun bindMainDirections(impl: MainScreenDirectionImpl): MainScreenDirection

    @Binds
    fun bindOrderScreen(impl: OrderScreenDirectionImpl): OrderScreenDirection

    @Binds
    fun bindsBasketScreen(impl: BasketScreenDirectionsImpl): BasketScreenDirections

    @Binds
    fun bindsProfileScreen(impl: ProfileScreenDirectionsImpl): ProfileScreenDirections

}