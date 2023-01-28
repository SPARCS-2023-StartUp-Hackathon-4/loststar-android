package com.sparcs.loststar.network.model

data class MyInfoResponse(
    val id: Long,
    val email: String,
    val nickname : String,
    val address: String,
    val profile : String,
    val fcmToken : String,
    val anchorStar : Int,
    val starPiece : Int,
    val boostItem : Int,
    val speakerItem : Int
)
