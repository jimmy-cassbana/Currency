package com.jimmy.currency.presentation.currency_convert

import android.util.Log
import com.jimmy.core_arch.domain.DataState
import com.jimmy.core_arch.presentation.viewmodel.MVIBaseViewModel
import com.jimmy.currency.domain.usecase.CurrencyConvertUseCase
import com.jimmy.currency.presentation.currency_details.popular.PopularCurrency
import com.jimmy.currency.presentation.currency_details.popular.PopularCurrencyEnum
import com.jimmy.currency.util.roundTwo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class CurrencyConvertViewModel @Inject constructor(
    private val convertCurrency: CurrencyConvertUseCase
) :
    MVIBaseViewModel<CurrencyConvertAction, CurrencyConvertResult, CurrencyConvertViewState>() {

    override val defaultViewState: CurrencyConvertViewState
        get() = CurrencyConvertViewState()

    override fun handleAction(action: CurrencyConvertAction): Flow<CurrencyConvertResult> {
        return flow {
            when (action) {
                is CurrencyConvertAction.GetConversionRates -> handleActionOfConvertCurrency(
                    action.base,
                    this
                )
            }
        }
    }

    private suspend fun handleActionOfConvertCurrency(
        base: String,
        flowCollector: FlowCollector<CurrencyConvertResult>
    ) {
        try {
            flowCollector.emit(CurrencyConvertResult.Loading)
            val dataState = convertCurrency(base)
            if (dataState is DataState.Success)
                dataState.data.let {
                    flowCollector.emit(CurrencyConvertResult.Success(it))
                }
            else if (dataState is DataState.Error) {
                flowCollector.emit(CurrencyConvertResult.Failure(dataState.throwable))
            }
        } catch (error: Throwable) {
            flowCollector.emit(CurrencyConvertResult.Failure(error))
        }
    }

    fun convertCurrency(baseVal: Double, toVal: Double, amount: Double) =
        ((amount * toVal) / baseVal).roundTwo()

    fun getPopularCurrencies(currencyMap: HashMap<String, Double>): Array<PopularCurrency> {
        var currencies = arrayOf<PopularCurrency>()
        PopularCurrencyEnum.values().forEach { currency ->
            currencyMap[currency.name]?.let {
                currencies += PopularCurrency(currency.name, it)
            }
        }
        return currencies
    }
}