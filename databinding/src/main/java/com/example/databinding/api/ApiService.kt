package com.example.databinding.api

import com.example.databinding.domain.OnSaleResult
import com.example.databinding.domain.ResultData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.sunofbeach.net/shop/"
    }

    @GET("onSell/{page}")
    suspend fun getOnSaleList(@Path("page") page: Int): ResultData<OnSaleResult>
}