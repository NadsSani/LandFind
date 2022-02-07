package com.nads.landfind.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nads.landfind.data.Land
import com.nads.landfind.model.ItemViewModel

class LandFinderAdapter(diffCallback: DiffUtil.ItemCallback<Land>) :
    PagingDataAdapter<Land, LandFinderAdapter.ViewHolder>(diffCallback) {
    var itemViewModels: List<ItemViewModel> = emptyList()
    private val viewTypeToLayoutId: MutableMap<Int, Int> = mutableMapOf()

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemViewModel: ItemViewModel) {
            binding.setVariable(BR.itemViewModel, itemViewModel)
        }

    }

    override fun getItemViewType(position: Int): Int {
        val item = itemViewModels[position]
        if (!viewTypeToLayoutId.containsKey(item.viewType)) {
            viewTypeToLayoutId[item.viewType] = item.layoutId
        }
        return item.viewType
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemViewModels[position])

    }
    fun updateItems(items: List<ItemViewModel>?) {
        itemViewModels = items ?: emptyList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            viewTypeToLayoutId[viewType] ?: 0,
            parent,
            false)
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int = itemViewModels.size
    object UserComparator : DiffUtil.ItemCallback<Land>() {
        override fun areItemsTheSame(oldItem: Land, newItem: Land): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Land, newItem: Land): Boolean {
            return oldItem == newItem
        }
    }


}