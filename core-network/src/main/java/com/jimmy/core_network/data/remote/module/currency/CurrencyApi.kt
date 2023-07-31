package com.jimmy.core_network.data.remote.module.currency

import com.jimmy.core_network.data.remote.model.response.ConvertionRateResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("api/latest")
    suspend fun convertCurrency(@Query("base") base: String? = null): Response<ConvertionRateResponse>

}