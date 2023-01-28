package com.sparcs.loststar.network

import android.util.Log
import com.sparcs.loststar.LostStarApplication
import com.sparcs.loststar.util.Constants.RETROFIT_TAG
import okhttp3.Interceptor
import javax.inject.Inject

class AuthenticationInterceptor@Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val accessToken = LostStarApplication.encryptedPrefs.getAccessToken() ?: ""
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken").build()
        Log.d(
            RETROFIT_TAG,
            "AuthenticationInterceptor - intercept() called / request header: ${accessToken}"
        )
        return chain.proceed(request)
    }
}