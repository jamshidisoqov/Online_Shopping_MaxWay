package uz.gita.online_shopping.presentation.screens.orders.history

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.online_shopping.R
import uz.gita.online_shopping.data.models.OrderData
import uz.gita.online_shopping.databinding.ListItemHistoryOrdersBinding
import uz.gita.online_shopping.utils.extensions.getFinanceType
import uz.gita.online_shopping.utils.extensions.getOrderName
import uz.gita.online_shopping.utils.extensions.inflate

// Created by Jamshid Isoqov an 10/13/2022
class HistoryOrderAdapter :
    ListAdapter<OrderData, HistoryOrderAdapter.ViewHolder>(activeItemCallback) {

    private var itemClickListener: ((OrderData) -> Unit)? = null

    fun setItemClickListener(block: (OrderData) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ListItemHistoryOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                tvOrderName.text = data.id.getOrderName()
                tvOrderPrice.text = data.allPrice.getFinanceType()
                tvOrderDate.text = data.date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemHistoryOrdersBinding.bind(parent.inflate(R.layout.list_item_active_orders))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}

private var activeItemCallback = object : DiffUtil.ItemCallback<OrderData>() {
    override fun areItemsTheSame(oldItem: OrderData, newItem: OrderData): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: OrderData, newItem: OrderData): Boolean =
        oldItem.id == newItem.id && oldItem.status == newItem.status

}