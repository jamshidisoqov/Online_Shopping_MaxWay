package uz.gita.online_shopping.presentation.screens.orders.active

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.online_shopping.R
import uz.gita.online_shopping.data.models.OrderData
import uz.gita.online_shopping.data.models.enums.OrderType
import uz.gita.online_shopping.databinding.ListItemActiveOrdersBinding
import uz.gita.online_shopping.utils.Basket
import uz.gita.online_shopping.utils.extensions.getOrderName
import uz.gita.online_shopping.utils.extensions.inflate

// Created by Jamshid Isoqov an 10/13/2022
class ActiveOrderAdapter :
    ListAdapter<OrderData, ActiveOrderAdapter.ViewHolder>(activeItemCallback) {

    inner class ViewHolder(private val binding: ListItemActiveOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                tvOrderName.text = data.id.getOrderName()
                tvOrderStatus.text = data.status.name
                val count = if (data.orderType == OrderType.SELF_CALL) 3 else 4
                orderStepView.setStepsNumber(count)
                orderStepView.setSteps(if (count == 3) Basket.onTheSelfList else Basket.deliveredList)
                orderStepView.go(data.status.ordinal, true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemActiveOrdersBinding.bind(parent.inflate(R.layout.list_item_active_orders))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}

private var activeItemCallback = object : DiffUtil.ItemCallback<OrderData>() {
    override fun areItemsTheSame(oldItem: OrderData, newItem: OrderData): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: OrderData, newItem: OrderData): Boolean =
        oldItem.id == newItem.id && oldItem.status == newItem.status

}