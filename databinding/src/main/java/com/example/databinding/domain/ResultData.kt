package com.example.databinding.domain

data class ResultData<T>(val success: Boolean, val code: Int, val message: String, val data: T)