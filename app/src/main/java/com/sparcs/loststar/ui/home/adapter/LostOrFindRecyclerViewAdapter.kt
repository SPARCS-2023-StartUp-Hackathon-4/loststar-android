package com.sparcs.loststar.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sparcs.loststar.databinding.ItemRecyclerLostFindBinding
import com.sparcs.loststar.network.model.CardResponse
import com.sparcs.loststar.network.model.TestEmergency
import com.sparcs.loststar.network.model.TestLostOrFind
import com.sparcs.loststar.ui.chatting.chatroom.ChatRoomListRVAdapter
import com.sparcs.loststar.util.GlideUtil


class LostOrFindRecyclerViewAdapter() : ListAdapter<CardResponse, LostOrFindRecyclerViewAdapter.LostOrFindHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LostOrFindHolder {
        return LostOrFindHolder(ItemRecyclerLostFindBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LostOrFindHolder, position: Int) {
        holder.setData(currentList[position])
    }

    inner class LostOrFindHolder(private val binding: ItemRecyclerLostFindBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: CardResponse) {
            binding.root.setOnClickListener {
                itemClick?.onClick(
                    data.id.toInt()
                )
            }
            binding.tvTitle.text = data.title
            binding.tvStar.text = data.reward
            GlideUtil.loadRadiusImage(binding.ivMain, data.image, 8)
            binding.tvDate.text = "${data.date} | ${data.time}"
            binding.tvLocation.text = data.location
            GlideUtil.loadRadiusImage(binding.ivMain, data.image, 16)
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<CardResponse>() {
            override fun areItemsTheSame(oldItem: CardResponse, newItem: CardResponse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CardResponse,
                newItem: CardResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


    interface ItemClick {
        fun onClick(id: Int)
    }

    var itemClick: ItemClick? = null

}