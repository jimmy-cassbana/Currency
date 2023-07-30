package com.jimmy.currency.presentation.currency_convert

import com.jimmy.core_arch.Action
import com.jimmy.currency.network.model.request.ConversionRatesRequest

sealed class CurrencyConvertAction : Action {

    data class GetConversionRates(val request: ConversionRatesRequest) : CurrencyConvertAction()
}