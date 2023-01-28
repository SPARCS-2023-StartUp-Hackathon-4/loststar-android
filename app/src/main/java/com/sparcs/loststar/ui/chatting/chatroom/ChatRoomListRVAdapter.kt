package com.sparcs.loststar.ui.chatting.chatroom


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sparcs.loststar.R
import com.sparcs.loststar.util.GlideUtil
import com.sparcs.loststar.util.PreferenceUtil

class ChatRoomListRVAdapter(val items: MutableList<ChatRoomInfoModel>) :
    RecyclerView.Adapter<ChatRoomListRVAdapter.ViewHolder>() {
    val userid = PreferenceUtil.prefs.getString("userid",  "")

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return when (viewType) {
            0 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_room_rv_item, parent, false)
                ViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_room_rv_item, parent, false)
                ViewHolder(view)
            }
        }
    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemClick != null) {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
        holder.bindItems(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].senderId == userid) {
            0
        } else {
            1
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nickname: TextView = itemView.findViewById(R.id.rv_chat_nickname)
        private val address: TextView = itemView.findViewById(R.id.rv_chat_address)
        private val title : TextView = itemView.findViewById(R.id.rv_chat_title)
        private val lostImg : ImageView = itemView.findViewById(R.id.rv_chat_title_img)

        fun bindItems(item: ChatRoomInfoModel) {
            GlideUtil.loadImage(lostImg, item.lostAndFoundImg)
            title.text = item.lostAndFoundTitle
            nickname.text = item.senderNickname
            address.text = item.senderAddress
        }
    }


}
