package com.jimmy.core_data.domain.repository

import com.jimmy.core_arch.domain.DataState
import com.jimmy.core_network.data.remote.model.response.ConvertionRateResponse

interface CurrencyRepository {
    suspend fun convertCurrency(base: String): DataState<ConvertionRateResponse>
}