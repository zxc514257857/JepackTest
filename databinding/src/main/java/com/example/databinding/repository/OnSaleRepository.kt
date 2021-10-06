package com.example.databinding.repository

import com.example.databinding.api.RetrofitClient

class OnSaleRepository {

    suspend fun getOnSaleListByPage(page: Int) = RetrofitClient.apiService.getOnSaleList(page)
}