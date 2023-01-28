package com.sparcs.loststar.network

import com.skydoves.sandwich.ApiResponse
import com.sparcs.loststar.network.model.CardResponse
import com.sparcs.loststar.network.model.CategoriesResponse
import com.sparcs.loststar.network.model.ListResponse
import com.sparcs.loststar.network.model.PageResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("/api/categories")
    suspend fun fetchCategories(): ApiResponse<CategoriesResponse>

    @GET("/lost-found/boosts")
    suspend fun getBoosts(
        @Query("type") type: String
    ): ApiResponse<ListResponse>

    @GET("/lost-found")
    suspend fun getList(
        @Query("type") type: String,
        @Query("category") category: String? = null,
        @Query("location") location: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): ApiResponse<PageResponse<CardResponse>>

    @POST("/kakao")
    suspend fun kakaoLogin(@Body request: KakaoLoginRequest): ApiResponse<KakaoLoginResponse>

    @GET("users/me")
    suspend fun fetchMyInfo(): ApiResponse<MyInfoResponse>

}