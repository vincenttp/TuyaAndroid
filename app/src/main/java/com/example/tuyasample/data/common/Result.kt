package com.example.tuyasample.data.common

sealed class ResultData<out T> {
    data class Success<out T>(val data: T) : ResultData<T>()
    data class Error(val code: String, val message: String) : ResultData<Nothing>()
    object Loading : ResultData<Nothing>()
}