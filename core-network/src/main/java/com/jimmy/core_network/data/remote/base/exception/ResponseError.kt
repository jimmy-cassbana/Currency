package com.jimmy.core_network.data.remote.base.exception

import androidx.annotation.Keep

@Keep
data class ResponseError(
    val statusCode: Int,
    val error: Error,
)
data class Error(
    val code: Int,
    val type: String,
    val info: String
)
