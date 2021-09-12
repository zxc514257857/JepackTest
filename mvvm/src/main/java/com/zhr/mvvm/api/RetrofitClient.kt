package com.zhr.mvvm.api

import com.zhr.mvvm.AppConstant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 创建Retrofit 以及提供Api Service
 */
object RetrofitClient {

    private val okhttpClient = OkHttpClient.Builder()
        // callTimeout = read + write + connect
        .callTimeout(AppConstant.OKHTTP_TIMEOUT, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient)
        .build()

    val apiService = retrofit.create(ApiService::class.java)!!
}