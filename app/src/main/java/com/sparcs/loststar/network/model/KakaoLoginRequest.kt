package com.sparcs.loststar.network.model

data class KakaoLoginRequest(
    val accessToken: String,
    val address: String = "강남",
    val profile: String,
    val fcmToken: String
)
