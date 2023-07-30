package com.jimmy.core_network.data.remote.module.currency

import com.jimmy.core_arch.domain.DataState
import com.jimmy.core_network.data.remote.model.response.ConvertionRateResponse

interface CurrencyRemoteDataSource {
    suspend fun convertCurrency(base: String): DataState<ConvertionRateResponse>
}