package com.jimmy.currency.presentation.currency_convert

import com.jimmy.core_arch.domain.DataState
import com.jimmy.core_arch.presentation.viewmodel.MVIBaseViewModel
import com.jimmy.currency.domain.usecase.CurrencyConvertUseCase
import com.jimmy.currency.network.model.request.ConversionRatesRequest
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

    override val defaultViewState = CurrencyConvertViewState()

    override fun handleAction(action: CurrencyConvertAction): Flow<CurrencyConvertResult> {
        return flow {
            when (action) {
                is CurrencyConvertAction.GetConversionRates -> handleActionOfConvertCurrency(
                    action.request,
                    this
                )
            }
        }
    }

    private suspend fun handleActionOfConvertCurrency(
        request: ConversionRatesRequest,
        flowCollector: FlowCollector<CurrencyConvertResult>
    ) {
        try {
            flowCollector.emit(CurrencyConvertResult.Loading)
            val dataState = convertCurrency(request)
            if (dataState is DataState.Success)
                dataState.data.let {
                    flowCollector.emit(CurrencyConvertResult.Success(it))
                }
            else if (dataState is DataState.Error)
                flowCollector.emit(CurrencyConvertResult.Failure(dataState.throwable))
        } catch (error: Throwable) {
            flowCollector.emit(CurrencyConvertResult.Failure(error))
        }
    }


}