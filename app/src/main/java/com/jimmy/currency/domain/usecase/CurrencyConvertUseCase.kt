package com.jimmy.currency.domain.usecase

import com.jimmy.core_arch.domain.DataState
import com.jimmy.core_arch.domain.ISuspendableUseCase
import com.jimmy.core_data.domain.repository.CurrencyRepository
import com.jimmy.core_network.data.remote.model.response.ConvertionRateResponse
import javax.inject.Inject

class CurrencyConvertUseCase @Inject constructor(
    private val repository: CurrencyRepository
): ISuspendableUseCase.WithParams<String, DataState<ConvertionRateResponse>> {
    override suspend fun invoke(input: String): DataState<ConvertionRateResponse> {
        return repository.convertCurrency(input)
    }
}