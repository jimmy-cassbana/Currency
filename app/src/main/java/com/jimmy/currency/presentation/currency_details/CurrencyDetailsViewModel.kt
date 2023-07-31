package com.jimmy.currency.presentation.currency_details

import androidx.lifecycle.ViewModel
import com.jimmy.currency.presentation.currency_details.popular.PopularCurrency

class CurrencyDetailsViewModel: ViewModel() {

    fun calculateConversionRates(
        currencyKey: String,
        currencyValue: Float,
        currencies: Array<PopularCurrency>
    ): List<PopularCurrency> {
        val currencyList = arrayListOf<PopularCurrency>()
        currencies.forEach {
            if (it.key != currencyKey) {
                val rate = it.value / currencyValue
                currencyList.add(PopularCurrency(it.key, rate))
            }
        }
        return currencyList
    }
}