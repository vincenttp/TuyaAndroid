package com.example.tuyasample.domain.usecase

import com.example.tuyasample.data.common.ResultData

abstract class BaseUseCase<T, Params> {
    private var params: Params? = null
    private var onSuccess: (T) -> Unit = {}
    private var onError: (code: String, message: String) -> Unit = { code, message -> }

    abstract suspend fun build(params: Params?): ResultData<T>

    fun addParams(params: Params) = apply {
        this.params = params
    }

    fun onSuccess(onSuccess: (T) -> Unit) = apply {
        this.onSuccess = onSuccess
    }

    fun onError(onError: (code: String, message: String) -> Unit) = apply {
        this.onError = onError
    }

    suspend fun execute() {
        when (val result = build(params)) {
            is ResultData.Error -> onError(result.code, result.message)
            is ResultData.Success -> onSuccess(result.data)
            ResultData.Loading -> TODO()
        }
    }
}