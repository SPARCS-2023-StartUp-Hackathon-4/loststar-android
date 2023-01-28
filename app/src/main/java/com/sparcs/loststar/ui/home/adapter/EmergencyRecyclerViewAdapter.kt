package com.sparcs.loststar.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sparcs.loststar.databinding.ItemRecyclerLostFindEmergencyBinding
import com.sparcs.loststar.network.model.CardResponse
import com.sparcs.loststar.network.model.TestEmergency


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
            binding.tvTitle.text = data.title
            binding.tvStar.text = 0.toString()
            binding.tvDate.text = "${data.date} | ${data.time}"
            binding.tvLocation.text = data.location
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
}