package com.jimmy.core_network.data.remote.base.exception

import android.content.Context
import com.jimmy.core_network.R
import java.net.HttpURLConnection.HTTP_GATEWAY_TIMEOUT

/**
 * Exception represents IO exception or Http exception with code [HTTP_GATEWAY_TIMEOUT]
 */
class NetworkException(val context: Context) : Exception() {
    override val message: String
        get() = context.getString(R.string.network_error_message)
}


/**
 * Exception represents Http Exception with error body of [ResponseError]
 */
class GeneralHttpException(
    val context: Context,
    private val responseErrorBody: ResponseError
) : Exception() {
    override val message: String
        get() = responseErrorBody.error.info
}

/**
 * Exception represents any unknown Exception
 */
class UnknownException(val context: Context) : Exception() {
    override val message: String
        get() = context.getString(R.string.unknown_error_message)
}

/**
 * Exception represents Unstable internet
 */
class UnstableInternetException(val context: Context) : Exception() {
    override val message: String
        get() = context.getString(R.string.unstable_network_error_message)
}


