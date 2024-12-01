package com.example.tuyasample.domain.usecase

import com.example.tuyasample.data.common.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseUseCaseFlow <T, Params> {
    var params: Params? = null
    private var onSuccess: (T) -> Unit = {}
    private var onError: (code: String, message: String) -> Unit = { code, message -> }
    private var onLoading: () -> Unit = {}

    abstract suspend fun build(): Flow<ResultData<T>>

    fun addParams(params: Params?) = apply {
        this.params = params
    }

    fun onSuccess(onSuccess: (T) -> Unit) = apply {
        this.onSuccess = onSuccess
    }

    fun onError(onError: (code: String, message: String) -> Unit) = apply {
        this.onError = onError
    }

    fun onLoading(onLoading: () -> Unit) = apply {
        this.onLoading = onLoading
    }

    suspend fun execute() {
        build().flowOn(Dispatchers.IO).collect{
            when (it){
                is ResultData.Error -> onError(it.code, it.message)
                is ResultData.Success -> onSuccess(it.data)
                ResultData.Loading -> onLoading()
            }
        }
    }
}