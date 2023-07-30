package com.jimmy.core_network.ext

import com.jimmy.core_arch.domain.DataState
import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.getDataState(): DataState<T> {
    return if (isSuccessful && body() != null) {
        DataState.Success(data = body()!!)
    } else
        DataState.Error(throwable = HttpException(this))
}