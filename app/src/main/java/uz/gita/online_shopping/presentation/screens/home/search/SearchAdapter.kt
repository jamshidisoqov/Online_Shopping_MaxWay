package uz.gita.online_shopping.presentation.screens.home.search

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.online_shopping.R
import uz.gita.online_shopping.data.models.ProductWithCount
import uz.gita.online_shopping.databinding.SearchListItemProductsBinding
import uz.gita.online_shopping.utils.extensions.getFinanceType
import uz.gita.online_shopping.utils.extensions.inflate

// Created by Jamshid Isoqov an 10/25/2022
class SearchAdapter :
    ListAdapter<ProductWithCount, SearchAdapter.ViewHolder>(itemProductsCallback) {

    private var itemBasketClickListener: ((ProductWithCount) -> Unit)? = null

    fun setItemClickListener(block: (ProductWithCount) -> Unit) {
        itemBasketClickListener = block
    }


    inner class ViewHolder(val binding: SearchListItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemBasketClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun onBind() {
            binding.apply {
                val data = getItem(absoluteAdapterPosition)
                Glide.with(binding.imageProduct.context)
                    .load(data.productData.imageUrl)
                    .placeholder(R.drawable.place)
                    .into(binding.imageProduct)
                tvProductName.text = data.productData.name
                tvProductPrice.text = data.productData.price.getFinanceType()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        SearchListItemProductsBinding.bind(
            parent.inflate(R.layout.search_list_item_products)
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()


}

private var itemProductsCallback = object : DiffUtil.ItemCallback<ProductWithCount>() {
    override fun areItemsTheSame(oldItem: ProductWithCount, newItem: ProductWithCount): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ProductWithCount, newItem: ProductWithCount): Boolean =
        oldItem.productData.id == newItem.productData.id && oldItem.productData.name == newItem.productData.name
}