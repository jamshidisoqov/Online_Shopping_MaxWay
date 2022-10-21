package uz.gita.online_shopping.presentation.screens.orders

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.online_shopping.presentation.screens.orders.active.ActiveOrdersPage
import uz.gita.online_shopping.presentation.screens.orders.history.HistoryOrdersPage

// Created by Jamshid Isoqov an 10/13/2022
class OrderPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int) =
        if (position == 0) ActiveOrdersPage()
        else HistoryOrdersPage()
}