package com.jimmy.currency.domain.repository

import com.jimmy.core_arch.domain.DataState
import com.jimmy.currency.network.model.request.ConversionRatesRequest
import com.jimmy.currency.network.model.response.ConvertionRateResponse

interface CurrencyRepository {
    suspend fun convertCurrency(request: ConversionRatesRequest): DataState<ConvertionRateResponse>
}