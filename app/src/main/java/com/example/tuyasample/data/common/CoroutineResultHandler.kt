package com.example.tuyasample.data.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

suspend fun <T> getResult(
    networkCall: suspend () -> T
): ResultData<T> = withContext(Dispatchers.IO) {
    try {
        val response = networkCall()
        ResultData.Success(response)
    } catch (e: Exception) {
        e.printStackTrace()
        ResultData.Error(
            code = "00",
            message = "Exception Error"
        )
    }
}

suspend fun <T> getResultThirdParty(
    networkCall: suspend () -> T
): ResultData<T> = withContext(Dispatchers.IO) {
    try {
        val response = networkCall()
        ResultData.Success(response)
    } catch (e: Exception) {
        e.printStackTrace()
        ResultData.Error(
            code = "00",
            message = e.message ?: "Unexpected Error"
        )
    }
}

fun <T> getResultFlow(
    networkCall: suspend () -> T
) = flow<ResultData<T>> {
    emit(ResultData.Loading)
    val data = networkCall()
    when {
        data != null -> emit(ResultData.Success(data))
    }
}
