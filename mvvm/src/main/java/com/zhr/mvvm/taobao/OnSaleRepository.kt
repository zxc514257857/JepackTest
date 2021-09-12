package com.zhr.mvvm.taobao

import com.zhr.mvvm.api.RetrofitClient
import com.zhr.mvvm.domain.OnSaleData
import com.zhr.mvvm.domain.ResultData1

/**
 * Repository 就类似于Model
 */
class OnSaleRepository {

    suspend fun getOnSaleList(page: Int): OnSaleData {
        return RetrofitClient.apiService.getOnSaleList(page).apiData()
    }

//    suspend fun getOnSaleList(page: Int): ResultData1 {
//        return RetrofitClient.apiService.getOnSaleList(page)
//    }
}