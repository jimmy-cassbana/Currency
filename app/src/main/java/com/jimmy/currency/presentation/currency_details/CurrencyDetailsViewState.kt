package com.jimmy.currency.presentation.currency_details

import com.jimmy.core_arch.ViewState
import com.jimmy.core_database.entity.HistorySelection

data class CurrencyDetailsViewState(
    val historyList: List<HistorySelection>? = null,
    val error: Throwable? = null
) : ViewState