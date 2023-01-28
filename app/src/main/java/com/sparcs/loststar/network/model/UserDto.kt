package com.sparcs.loststar.network.model

data class UserDto(
    val id: Long = -1L,
    val email: String = "",
    val nickname : String = "",
    val address: String = "",
    val profile : String = "",
    val fcmToken : String = "",
    val anchorStar : Int = 0,
    val starPiece : Int = 0,
    val boostItem : Int = 0,
    val speakerItem : Int = 0
)
