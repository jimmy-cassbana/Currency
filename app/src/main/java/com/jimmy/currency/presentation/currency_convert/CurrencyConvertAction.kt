package com.jimmy.currency.presentation.currency_convert

import com.jimmy.core_arch.Action

sealed class CurrencyConvertAction : Action {

    data class GetConversionRates(val base: String) : CurrencyConvertAction()
}