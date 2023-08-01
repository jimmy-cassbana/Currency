package com.jimmy.currency.presentation.currency_details

import com.jimmy.core_arch.Result
import com.jimmy.core_database.entity.HistorySelection

sealed class CurrencyDetailsResult : Result<CurrencyDetailsViewState> {

    data class Success(val history: List<HistorySelection>) : CurrencyDetailsResult() {
        override fun reduce(
            defaultState: CurrencyDetailsViewState,
            oldState: CurrencyDetailsViewState
        ): CurrencyDetailsViewState {
            return oldState.copy(historyList = history)
        }
    }

    data class Failure(val error: Throwable) : CurrencyDetailsResult() {
        override fun reduce(
            defaultState: CurrencyDetailsViewState,
            oldState: CurrencyDetailsViewState
        ): CurrencyDetailsViewState {
            return oldState.copy(error = error)
        }
    }
}