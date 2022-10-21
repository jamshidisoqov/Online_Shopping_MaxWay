package uz.gita.online_shopping.presentation.screens.basket

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.online_shopping.R
import uz.gita.online_shopping.data.models.ProductWithCount
import uz.gita.online_shopping.databinding.BasketListItemProductsBinding
import uz.gita.online_shopping.presentation.screens.home.itemCallback
import uz.gita.online_shopping.utils.extensions.getFinanceType
import uz.gita.online_shopping.utils.extensions.inflate

// Created by Jamshid Isoqov an 10/18/2022
class BasketAdapter : ListAdapter<ProductWithCount, BasketAdapter.ViewHolder>(itemCallback) {

    private var itemRemovedListener: ((ProductWithCount) -> Unit)? = null
    private var itemDeleteListener: ((ProductWithCount) -> Unit)? = null
    private var itemAddListener: ((ProductWithCount) -> Unit)? = null


    fun setItemRemovedListener(block: (ProductWithCount) -> Unit) {
        itemRemovedListener = block
    }

    fun setItemDeleteListener(block: (ProductWithCount) -> Unit) {
        itemDeleteListener = block
    }

    fun setItemAddListener(block: (ProductWithCount) -> Unit) {
        itemAddListener = block
    }

    inner class ViewHolder(private val binding: BasketListItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                imageAddProduct.setOnClickListener {
                    itemAddListener?.invoke(getItem(absoluteAdapterPosition))
                }
                imageMinusProduct.setOnClickListener {
                    itemRemovedListener?.invoke(getItem(absoluteAdapterPosition))
                }
                imageProductDelete.setOnClickListener {
                    itemDeleteListener?.invoke(getItem(absoluteAdapterPosition))
                }
            }
        }

        fun onBind() {
            binding.apply {
                val data = getItem(absoluteAdapterPosition)

                Glide.with(binding.imageProduct.context)
                    .load(data.productData.imageUrl)
                    .placeholder(R.drawable.place)
                    .into(binding.imageProduct)

                binding.tvProductName.text = data.productData.name

                binding.tvProductPrice.text = data.productData.price.getFinanceType()

                binding.tvProductCount.text = data.count.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        BasketListItemProductsBinding.bind(parent.inflate(R.layout.basket_list_item_products))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}