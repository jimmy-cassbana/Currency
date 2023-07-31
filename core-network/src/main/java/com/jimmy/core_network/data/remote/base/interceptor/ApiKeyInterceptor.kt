package com.jimmy.core_network.data.remote.base.interceptor

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor(
    @ApplicationContext val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder()
                .url(chain.request().url.newBuilder().addQueryParameter(API_KEY, VALUE).build())
                .build()
        return chain.proceed(request)
    }

    companion object {
        const val API_KEY = "access_key"
        const val VALUE = "1709d95d70a1074fdd006477f196dbad"

    }
}



