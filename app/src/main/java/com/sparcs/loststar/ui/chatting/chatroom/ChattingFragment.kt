package com.sparcs.loststar.ui.chatting.chatroom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.sparcs.loststar.databinding.FragmentChattingBinding
import com.sparcs.loststar.ui.chatting.chat.ChatInsideActivity
import com.sparcs.loststar.util.FBRef
import com.sparcs.loststar.util.PreferenceUtil
import java.lang.Exception

class ChattingFragment : Fragment() {

    private lateinit var chatRoomListRVAdapter: ChatRoomListRVAdapter
    private val chatRoomList = mutableListOf<ChatRoomInfoModel>()
    private val chatRoomListKey = mutableListOf<String>()
    val userid = PreferenceUtil.prefs.getString("userid",  "")
    private val binding: FragmentChattingBinding by lazy {
        FragmentChattingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatRoomListRVAdapter = ChatRoomListRVAdapter(chatRoomList)
        binding.rvChatRoomList.adapter = chatRoomListRVAdapter
        binding.rvChatRoomList.layoutManager = LinearLayoutManager(context)

        getChatRoomListData()

        chatRoomListRVAdapter.itemClick = object : ChatRoomListRVAdapter.ItemClick {

            override fun onClick(view: View, position: Int) {
                val intent = Intent(requireContext(), ChatInsideActivity::class.java)
                intent.putExtra("roomKey", chatRoomListKey[position])
                startActivity(intent)
            }
        }
    }

    private fun getChatRoomListData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                chatRoomList.clear()
                for(dataModel in snapshot.children){
                    try {
                        val item = dataModel.getValue(ChatRoomInfoModel::class.java)
                        if (item != null) {

                            if (item.senderId == userid || item.receiverId == userid){
                                chatRoomList.add(item)
                                chatRoomListKey.add(dataModel.key.toString())
                            }
                        }
                    }catch (e : Exception){
                        Log.d("AAA", e.message.toString())
                    }
                }
                chatRoomList.reverse()
                chatRoomListKey.reverse()
                chatRoomListRVAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        FBRef.chatRoomListRef.addValueEventListener(postListener)
    }
}