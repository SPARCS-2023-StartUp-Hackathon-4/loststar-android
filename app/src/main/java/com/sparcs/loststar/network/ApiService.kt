package com.sparcs.loststar.network

import com.skydoves.sandwich.ApiResponse
import com.sparcs.loststar.network.model.*
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

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

    @GET("/users/me")
    suspend fun fetchMyInfo(): ApiResponse<UserDto>

    @GET("/lost-found/{id}")
    suspend fun getLostOrFound(@Path("id") id: Int): ApiResponse<LostFoundResponse>

    @POST("/lost-found")
    suspend fun postLostOrFound(@Body request: LostFoundRequest): ApiResponse<IdResponse>

    @POST("/upload")
    suspend fun uploadImage(@Part("image") image: MultipartBody.Part): ApiResponse<ImageResponse>

}