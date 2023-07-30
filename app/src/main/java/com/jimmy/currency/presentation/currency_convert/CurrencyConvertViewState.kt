package com.jimmy.currency.presentation.currency_convert

import com.jimmy.core_arch.ViewState
import com.jimmy.core_network.data.remote.model.response.ConvertionRateResponse

data class CurrencyConvertViewState(
    val loading: Boolean = false,
    val result: ConvertionRateResponse? = null,
    val error: Throwable? = null
) : ViewState