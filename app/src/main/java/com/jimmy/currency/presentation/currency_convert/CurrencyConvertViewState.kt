package com.jimmy.currency.presentation.currency_convert

import com.jimmy.core_arch.ViewState
import com.jimmy.currency.network.model.response.ConvertionRateResponse

data class CurrencyConvertViewState(
    val loading: Boolean = false,
    val result: ConvertionRateResponse? = null,
    val error: Throwable? = null
) : ViewState