package com.jimmy.currency.presentation.currency_convert

import com.jimmy.core_arch.Action
import com.jimmy.core_database.entity.HistorySelection

sealed class CurrencyConvertAction : Action {
    data class GetConversionRates(val base: String) : CurrencyConvertAction()
    data class SaveHistorySelection(val selection: HistorySelection) : CurrencyConvertAction()
}