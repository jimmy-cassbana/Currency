package com.jimmy.core_network.ext

import android.content.Context
import com.google.gson.Gson
import com.jimmy.core_network.data.remote.base.exception.GeneralHttpException
import com.jimmy.core_network.data.remote.base.exception.NetworkException
import com.jimmy.core_network.data.remote.base.exception.UnknownException
import com.jimmy.core_network.data.remote.base.exception.UnstableInternetException
import com.jimmy.core_network.data.remote.base.exception.ResponseError
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.nio.charset.Charset

fun Throwable.getCustomException(context: Context): Throwable {
    return when (this) {
        is HttpException -> getHttpException(this, context)

        is SocketTimeoutException,
        is UnknownHostException,
        is UnsatisfiedLinkError,
        is ConnectException -> {
            NetworkException(context)
        }

        else -> {
            UnknownException(context)
        }
    }
}

fun getHttpException(httpException: HttpException, context: Context): Throwable {
    return when {
        httpException.code() == HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> {
            NetworkException(context)
        }
        httpException.code() == 0 || httpException.code() == HttpURLConnection.HTTP_CLIENT_TIMEOUT -> {
            UnstableInternetException(context)
        }
        else -> {
            getGeneralHttpException(httpException, context)
        }
    }
}

fun getGeneralHttpException(httpException: HttpException, context: Context): Throwable {
    return try {
        val errorBody = httpException.cloneErrorBody()
        val responseBodyError = Gson().fromJson(
            errorBody,
            ResponseError::class.java
        )
        if (responseBodyError.statusCode == -1 && responseBodyError.error.info.isEmpty()) {
            httpException
        } else
            GeneralHttpException(context, responseBodyError)
    } catch (e: Exception) {
        httpException
    }
}

fun HttpException.cloneErrorBody(): String? {
    val source = response()?.errorBody()?.source()
    source?.request(Long.MAX_VALUE) // request the entire body.
    val buffer = source?.buffer
    // clone buffer before reading from it
    return buffer?.clone()?.readString(Charset.forName("UTF-8"))
}
