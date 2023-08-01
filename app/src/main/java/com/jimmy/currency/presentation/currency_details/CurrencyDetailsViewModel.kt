package com.jimmy.currency.presentation.currency_details

import com.jimmy.core_arch.presentation.viewmodel.MVIBaseViewModel
import com.jimmy.currency.domain.usecase.GetHistorySelectionUseCase
import com.jimmy.currency.presentation.currency_details.popular.PopularCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class CurrencyDetailsViewModel @Inject constructor(
    private val getHistorySelectionUseCase: GetHistorySelectionUseCase
) :
    MVIBaseViewModel<CurrencyDetailsAction, CurrencyDetailsResult, CurrencyDetailsViewState>() {

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

    override val defaultViewState: CurrencyDetailsViewState
        get() = CurrencyDetailsViewState()

    override fun handleAction(action: CurrencyDetailsAction): Flow<CurrencyDetailsResult> {
        return flow {
            when (action) {
                CurrencyDetailsAction.GetHistorySelection -> handleActionOfGetHistorySelection(
                    this
                )
            }
        }
    }

    private suspend fun handleActionOfGetHistorySelection(flowCollector: FlowCollector<CurrencyDetailsResult>) {
        try {
            val historyList = getHistorySelectionUseCase.invoke()
            historyList?.let {
                flowCollector.emit(CurrencyDetailsResult.Success(it))
            }
        } catch (error: Throwable) {
            flowCollector.emit(CurrencyDetailsResult.Failure(error))
        }
    }


}