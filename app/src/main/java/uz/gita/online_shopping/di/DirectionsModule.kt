package uz.gita.online_shopping.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.online_shopping.directions.*
import uz.gita.online_shopping.directions.impl.*

// Created by Jamshid Isoqov an 10/12/2022
@Module
@InstallIn(ViewModelComponent::class)
interface DirectionsModule {

    @Binds
    fun bindMainDirections(impl: MainScreenDirectionImpl): MainScreenDirection

    @Binds
    fun bindOrderScreen(impl: OrderScreenDirectionImpl): OrderScreenDirection

    @Binds
    fun bindsBasketScreen(impl: BasketScreenDirectionsImpl): BasketScreenDirection

    @Binds
    fun bindsProfileScreen(impl: ProfileScreenDirectionsImpl): ProfileScreenDirection

    @Binds
    fun bindSplashScreen(impl: SplashScreenDirectionsImpl): SplashScreenDirection

    @Binds
    fun bindLoginScreen(impl: LoginScreenDirectionImpl): LoginScreenDirection

    @Binds
    fun bindPasswordCheckoutScreen(impl: PasswordCheckoutScreenDirectionImpl): PasswordCheckoutScreenDirection

    @Binds
    fun bindProductDetailsScreen(impl: ProductDetailsDirectionImpl): ProductDetailsDirection

    @Binds
    fun bindOrderProductsScreen(impl: OrderProductDirectionImpl): OrderProductDirection

    @Binds
    fun bindBranchScreen(impl: BranchesDirectionImpl): BranchesDirection

    @Binds
    fun bindSearchProducts(impl: SearchScreenDirectionImpl): SearchScreenDirection

}