package com.sparcs.loststar.util

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class FBRef {
    companion object{
        private val database = Firebase.database

        val chatRoomListRef = database.getReference("chatroom_list")
        val chatRef = database.getReference("chat")
    }
}
