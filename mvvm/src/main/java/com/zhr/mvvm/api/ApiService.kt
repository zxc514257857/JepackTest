package com.zhr.mvvm.api

import com.zhr.mvvm.domain.OnSaleData
import com.zhr.mvvm.domain.ResultData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.sunofbeach.net/shop/"
    }

    @GET("onSell/{page}")
    suspend fun getOnSaleList(@Path("page") page: Int): ResultData<OnSaleData>

//    @GET("onSell/{page}")
//    suspend fun getOnSaleList(@Path("page") page: Int): ResultData1
}

























