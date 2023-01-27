package com.sparcs.loststar.network

import com.skydoves.sandwich.ApiResponse
import com.sparcs.loststar.network.model.CategoriesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/api/categories")
    suspend fun fetchCategories(): ApiResponse<CategoriesResponse>
}