package uz.gita.online_shopping.presentation.screens.orders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import uz.gita.online_shopping.R
import uz.gita.online_shopping.databinding.ScreenOrdersBinding

// Created by Jamshid Isoqov an 10/13/2022
class OrdersScreen : Fragment(R.layout.screen_orders) {

    private val viewBinding: ScreenOrdersBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.pagerOrders.adapter = OrderPagerAdapter(requireActivity())
        viewBinding.apply {
            TabLayoutMediator(tabBarOrders, pagerOrders) { tab, pos ->
                tab.apply {
                    text = if (pos == 0) {
                        resources.getString(R.string.active)
                    } else {
                        resources.getString(R.string.history)
                    }
                }
            }.attach()

        }
    }


}