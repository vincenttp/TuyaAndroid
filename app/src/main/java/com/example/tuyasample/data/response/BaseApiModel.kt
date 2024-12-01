package com.example.androidjetpack.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseApiModel<T>(
    val results: T
)
