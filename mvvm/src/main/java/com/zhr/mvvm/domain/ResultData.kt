package com.zhr.mvvm.domain

import com.zhr.mvvm.api.ApiException

data class ResultData<T>(
    val success: Boolean,
    val code: Int,
    val message: String,
    val data: T,
) {
    companion object {
        const val CODE_SUCCESS = 10000
    }

    /**
     * 对数据Bean里面共同的头部进行了统一的处理
     * 而不需要再对单个进行判断处理，比如 ResultData1
     */
    fun apiData(): T {
        if (code == CODE_SUCCESS) {
            return data
        } else {
            throw ApiException(code, message)
        }
    }
}