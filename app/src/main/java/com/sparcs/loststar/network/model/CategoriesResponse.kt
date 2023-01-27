package com.sparcs.loststar.network.model

data class CategoriesResponse(
    val data: Categories
)

data class Categories(
    val categories: List<Category>
)

data class Category(
    val id: Int,
    val name: String,
    val icon: String
)
