package com.sparcs.loststar.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sparcs.loststar.databinding.ItemRecyclerLostFindBinding
import com.sparcs.loststar.network.model.TestEmergency
import com.sparcs.loststar.network.model.TestLostOrFind
import com.sparcs.loststar.util.GlideUtil


class LostOrFindRecyclerViewAdapter() : ListAdapter<TestLostOrFind, LostOrFindRecyclerViewAdapter.LostOrFindHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LostOrFindHolder {
        return LostOrFindHolder(ItemRecyclerLostFindBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LostOrFindHolder, position: Int) {
        holder.setData(currentList[position])
    }

    inner class LostOrFindHolder(private val binding: ItemRecyclerLostFindBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: TestLostOrFind) {
            binding.tvTitle.text = data.mainTitle
            binding.tvStar.text = data.star.toString()
            binding.tvDate.text = "${data.date} | ${data.time}"
            binding.tvLocation.text = data.location
            GlideUtil.loadRadiusImage(binding.ivMain, data.mainImageUrl, 16)
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<TestLostOrFind>() {
            override fun areItemsTheSame(oldItem: TestLostOrFind, newItem: TestLostOrFind): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TestLostOrFind,
                newItem: TestLostOrFind
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}