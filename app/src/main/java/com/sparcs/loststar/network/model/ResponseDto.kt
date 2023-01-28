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
    val category: Category,
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
    val writer: MyInfoResponse,
    val title: String,
    val category: String,
    val location: String,
    val locationDetail: String,
    val date: String,
    val time: String,
    val image: String,
    val description: String,
    val useBoost: Boolean,
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