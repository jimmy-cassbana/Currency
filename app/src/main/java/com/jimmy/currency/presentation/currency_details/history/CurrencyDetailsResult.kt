package com.jimmy.currency.presentation.currency_details.history

import com.jimmy.core_arch.Result

sealed class CurrencyDetailsResult : Result<CurrencyDetailsViewState> {


    data class Failure(val error: Throwable) : CurrencyDetailsResult() {
        override fun reduce(
            defaultState: CurrencyDetailsViewState,
            oldState: CurrencyDetailsViewState
        ): CurrencyDetailsViewState {
            return oldState.copy(error = error)
        }
    }
}