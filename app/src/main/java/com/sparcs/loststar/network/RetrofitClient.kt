package com.sparcs.loststar.network

import android.util.Log
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.sparcs.loststar.util.Constants.BASE_URL
import com.sparcs.loststar.util.Constants.RETROFIT_TAG
import com.sparcs.loststar.util.isJsonArray
import com.sparcs.loststar.util.isJsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var instance: ApiService? = null

    fun getApiService(): ApiService {
        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(provideHttpClient(AuthenticationInterceptor(), provideLoggingInterceptor()))
                .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        return instance!!
    }

    private fun provideHttpClient(
        authenticationInterceptor: AuthenticationInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authenticationInterceptor)
            .build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            when {
                message.isJsonObject() ->
                    Log.d(RETROFIT_TAG, JSONObject(message).toString(4))
                message.isJsonArray() ->
                    Log.d(RETROFIT_TAG, JSONArray(message).toString(4))
                else ->
                    Log.d(RETROFIT_TAG, "CONNECTION INFO -> $message")
            }
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}