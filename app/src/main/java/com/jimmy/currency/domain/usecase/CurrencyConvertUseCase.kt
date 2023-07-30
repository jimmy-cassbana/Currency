package com.jimmy.currency.domain.usecase

import com.jimmy.core_arch.domain.DataState
import com.jimmy.core_arch.domain.ISuspendableUseCase
import com.jimmy.currency.domain.repository.CurrencyRepository
import com.jimmy.currency.network.model.request.ConversionRatesRequest
import com.jimmy.currency.network.model.response.ConvertionRateResponse
import javax.inject.Inject

class CurrencyConvertUseCase @Inject constructor(
    private val repository: CurrencyRepository,
): ISuspendableUseCase.WithParams<ConversionRatesRequest, DataState<ConvertionRateResponse>> {
    override suspend fun invoke(input: ConversionRatesRequest): DataState<ConvertionRateResponse> {
        return repository.convertCurrency(input)
    }
}