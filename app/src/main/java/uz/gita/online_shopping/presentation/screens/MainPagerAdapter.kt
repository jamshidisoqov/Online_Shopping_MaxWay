package uz.gita.online_shopping.presentation.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.online_shopping.presentation.screens.home.HomeScreen
import uz.gita.online_shopping.presentation.screens.orders.OrdersScreen
import uz.gita.online_shopping.presentation.screens.profile.ProfileScreen

// Created by Jamshid Isoqov an 10/24/2022
class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> HomeScreen()
            1 -> OrdersScreen()
            else -> ProfileScreen()
        }
}