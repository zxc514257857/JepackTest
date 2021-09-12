package com.zhr.mvvm.api

data class ApiException(val code: Int, override val message: String?) : RuntimeException() {
}