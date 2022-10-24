package uz.gita.online_shopping.presentation.screens.home

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.online_shopping.R
import uz.gita.online_shopping.data.models.ProductWithCount
import uz.gita.online_shopping.databinding.ListItemProductsBinding
import uz.gita.online_shopping.utils.extensions.getFinanceType
import uz.gita.online_shopping.utils.extensions.inflate

// Created by Jamshid Isoqov an 10/10/2022
class ProductsAdapter : ListAdapter<ProductWithCount, ProductsAdapter.ViewHolder>(itemCallback) {

    private var itemBasketClickListener: ((ProductWithCount) -> Unit)? = null

    fun setItemBasketClickListener(block: (ProductWithCount) -> Unit) {
        itemBasketClickListener = block
    }

    private var openBasketScreenListener: (() -> Unit)? = null

    fun setOpenBasketClickListener(block: () -> Unit) {
        openBasketScreenListener = block
    }

    private var openProductDetailListener: ((ProductWithCount) -> Unit)? = null

    fun setOpenProductDetailsListener(block: (ProductWithCount) -> Unit) {
        openProductDetailListener = block
    }

    inner class ViewHolder(private val binding: ListItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                btnBasket.setOnClickListener {
                    val data = getItem(absoluteAdapterPosition)
                    if (data.count > 0) {
                        openBasketScreenListener?.invoke()
                    } else
                        itemBasketClickListener?.invoke(data)
                }
                root.setOnClickListener {
                    openProductDetailListener?.invoke(getItem(absoluteAdapterPosition))
                }
            }
        }

        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                tvProductName.text = data.productData.name
                tvProductPrice.text = data.productData.price.getFinanceType()
                btnBasket.apply {
                    if (data.count == 0) {
                        text = resources.getString(R.string.to_basket)
                        setTextColor(ColorStateList.valueOf(R.color.white))
                        setBackgroundResource(R.drawable.bg_to_basket_btn)
                    } else {
                        setTextColor(R.color.black)
                        setBackgroundResource(R.drawable.bg_in_basket_btn)
                        text = resources.getString(R.string.in_basket)
                    }
                }
                Glide.with(binding.imageProductsItem.context)
                    .load(data.productData.imageUrl)
                    .placeholder(R.drawable.place)
                    .into(binding.imageProductsItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemProductsBinding.bind(parent.inflate(R.layout.list_item_products))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}


val itemCallback = object : DiffUtil.ItemCallback<ProductWithCount>() {
    override fun areItemsTheSame(oldItem: ProductWithCount, newItem: ProductWithCount) =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: ProductWithCount,
        newItem: ProductWithCount
    ): Boolean = oldItem.count == newItem.count && oldItem.productData.id == newItem.productData.id
}

