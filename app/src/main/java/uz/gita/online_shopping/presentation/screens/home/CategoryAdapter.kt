package uz.gita.online_shopping.presentation.screens.home

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.online_shopping.R
import uz.gita.online_shopping.data.models.CategoryData
import uz.gita.online_shopping.databinding.ListItemCategoryBinding
import uz.gita.online_shopping.utils.extensions.inflate

// Created by Jamshid Isoqov an 10/10/2022
class CategoryAdapter :
    ListAdapter<CategoryData, CategoryAdapter.ViewHolder>(categoryItemCallback) {

    var selectedPos = -1

    private var categorySelectedListener: ((CategoryData) -> Unit)? = null

    fun setCategoryListener(block: (CategoryData) -> Unit) {
        categorySelectedListener = block
    }

    inner class ViewHolder(private val binding: ListItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.tvCategoryName.setOnClickListener {
                selectedPos = if (selectedPos == absoluteAdapterPosition) {
                    -1
                } else {
                    notifyItemChanged(selectedPos)
                    absoluteAdapterPosition
                }
                categorySelectedListener?.invoke(getItem(absoluteAdapterPosition))
                notifyItemChanged(absoluteAdapterPosition)
            }
        }


        fun onBind() {
            binding.apply {
                tvCategoryName.apply {
                    if (selectedPos == absoluteAdapterPosition) {
                        setBackgroundResource(R.drawable.bg_category_item_selected)
                        setTextColor(Color.parseColor("#ffffff"))
                    } else {
                        setBackgroundResource(R.drawable.bg_category_item_unselected)
                        setTextColor(Color.parseColor("#000000"))
                    }
                    text = getItem(absoluteAdapterPosition).name
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemCategoryBinding.bind(parent.inflate(R.layout.list_item_category))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()
}

private val categoryItemCallback = object : DiffUtil.ItemCallback<CategoryData>() {
    override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData) = oldItem == newItem

    override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean =
        oldItem.id == newItem.id &&
                oldItem.name == newItem.name
}