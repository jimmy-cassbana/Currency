package com.jimmy.currency.presentation.currency_convert

import com.jimmy.core_arch.Result
import com.jimmy.core_network.data.remote.model.response.ConvertionRateResponse

sealed class CurrencyConvertResult : Result<CurrencyConvertViewState> {

    object Loading : CurrencyConvertResult() {
        override fun reduce(
            defaultState: CurrencyConvertViewState,
            oldState: CurrencyConvertViewState
        ) = defaultState.copy(loading = true)
    }

    data class Success(val result: ConvertionRateResponse) : CurrencyConvertResult() {
        override fun reduce(
            defaultState: CurrencyConvertViewState,
            oldState: CurrencyConvertViewState
        ) = defaultState.copy(
            loading = false,
            result = result
        )
    }

    data class Failure(val error: Throwable) : CurrencyConvertResult() {
        override fun reduce(
            defaultState: CurrencyConvertViewState,
            oldState: CurrencyConvertViewState
        ) = defaultState.copy(
            loading = false,
            error = error
        )
    }

}