package com.sparcs.loststar.network

import com.skydoves.sandwich.ApiResponse
import com.sparcs.loststar.network.model.CategoriesResponse
import com.sparcs.loststar.network.model.KakaoLoginRequest
import com.sparcs.loststar.network.model.KakaoLoginResponse
import com.sparcs.loststar.network.model.MyInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("/kakao")
    suspend fun kakaoLogin(@Body request: KakaoLoginRequest): ApiResponse<KakaoLoginResponse>

    @GET("users/me")
    suspend fun fetchMyInfo(): ApiResponse<MyInfoResponse>
}