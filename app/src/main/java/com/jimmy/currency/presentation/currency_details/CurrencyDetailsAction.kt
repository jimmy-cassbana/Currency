package com.jimmy.currency.presentation.currency_details

import com.jimmy.core_arch.Action

sealed class CurrencyDetailsAction : Action {
    object GetHistorySelection : CurrencyDetailsAction()
}