package com.sparcs.loststar.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sparcs.loststar.databinding.ItemRecyclerLostFindEmergencyBinding
import com.sparcs.loststar.network.model.CardResponse
import com.sparcs.loststar.network.model.TestEmergency
import com.sparcs.loststar.util.GlideUtil


class EmergencyRecyclerViewAdapter() : ListAdapter<CardResponse, EmergencyRecyclerViewAdapter.EmergencyHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmergencyHolder {
        return EmergencyHolder(ItemRecyclerLostFindEmergencyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EmergencyHolder, position: Int) {
        holder.setData(currentList[position])
    }

    inner class EmergencyHolder(private val binding: ItemRecyclerLostFindEmergencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: CardResponse) {
            binding.root.setOnClickListener {
                itemClick?.onClick(
                    data.id.toInt()
                )
            }
            GlideUtil.loadRadiusImage(binding.ivMain, data.image, 8)
            binding.tvTitle.text = data.title
            binding.tvStar.text = data.reward
            binding.tvDate.text = "${data.date} | ${data.time}"
            binding.tvLocation.text = data.location

            if(data.reward == "0") {
                binding.clStar.visibility = View.INVISIBLE
            }
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