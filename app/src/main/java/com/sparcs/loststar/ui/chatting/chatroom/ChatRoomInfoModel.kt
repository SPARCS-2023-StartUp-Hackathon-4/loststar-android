package com.sparcs.loststar.ui.chatting.chatroom

data class ChatRoomInfoModel(
    val senderId : String? = "",
    val senderNickname : String? = "",
    val senderProfile : String? = "",
    val senderAddress : String? = "",
    val receiverId : String? = "",
    val receiverNickname : String? = "",
    val receiverProfile : String? = "",
    val receiverAddress : String? = "",
    val lostAndFoundTitle : String? = "",
    val lostAndFoundImg : String? = "",
)