package uz.gita.online_shopping.presentation.screens.branches

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.online_shopping.R
import uz.gita.online_shopping.data.models.BranchData
import uz.gita.online_shopping.databinding.ListItemBranchesBinding
import uz.gita.online_shopping.utils.extensions.inflate

// Created by Jamshid Isoqov an 10/25/2022
class BranchesAdapter : ListAdapter<BranchData, BranchesAdapter.ViewHolder>(itemBranchesCallback) {

    private var itemClickListener: ((BranchData) -> Unit)? = null

    fun setItemClickListener(block: (BranchData) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ListItemBranchesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                tvBranchName.text = "MaxWay|${data.name}"
                tvBranchLocation.text = data.way
                tvBranchPhone.text = data.phone
                tvBranchTargetTime.text = data.schedule
                Glide.with(root.context)
                    .load(data.imageUrl)
                    .placeholder(R.drawable.place)
                    .into(imageBranch)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemBranchesBinding.bind(parent.inflate(R.layout.list_item_branches))
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

}

private val itemBranchesCallback = object : DiffUtil.ItemCallback<BranchData>() {
    override fun areItemsTheSame(oldItem: BranchData, newItem: BranchData): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: BranchData, newItem: BranchData): Boolean =
        oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.address == newItem.address

}