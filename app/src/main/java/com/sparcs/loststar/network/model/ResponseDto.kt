package com.sparcs.loststar.network.model

data class IdResponse(
    val id: Long,
)

data class ImageResponse(
    val image: String
)

data class LostFoundRequest(
    val type: String,
    val title: String,
    val category: String,
    val location: String,
    val locationDetail: String,
    val date: String,
    val time: String,
    val image: String,
    val description: String,
    val reward: Int = 0,
    val boost: Boolean,
) {
}

data class PageResponse<T>(
    val totalPages: Int,
    val totalElements: Long,
    val last: Boolean,
    val content: List<T>
) {
}

data class LostFoundResponse(
    val writer: UserDto,
    val title: String,
    val category: String,
    val location: String,
    val locationDetail: String,
    val date: String,
    val time: String,
    val image: String,
    val description: String,
    val useBoost: Boolean,
    val reward: Int,
) {
}

data class CardResponse(
    val id: Long,
    val image: String,
    val title: String,
    val location: String,
    val date: String,
    val time: String,
    val boost: Boolean,
    val reward: String
) {
}

data class ListResponse(
    val list: List<CardResponse>
)

data class UseSpeakerRequest(
    val id: Long
)

data class ResolveRequest(
    val userId: Long,
    val reward: Int
)