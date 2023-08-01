package com.jimmy.currency.presentation.currency_details

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.jimmy.currency.base.AppBaseFragment
import com.jimmy.currency.databinding.FragmentCurrencyDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CurrencyDetailsFragment :
    AppBaseFragment<FragmentCurrencyDetailsBinding>(FragmentCurrencyDetailsBinding::inflate) {

    private val args: CurrencyDetailsFragmentArgs by navArgs()
    private val viewModel: CurrencyDetailsViewModel by viewModels()

    override fun init() {
        super.init()
        val rates = viewModel.calculateConversionRates(
            args.currencyKey, args.currencyValue, args.currencies)
        binding.ratesRecycler.adapter = CurrencyRatesAdapter(rates.take(10))
        viewModel.executeAction(CurrencyDetailsAction.GetHistorySelection)
    }

    override fun subscribe() {
        super.subscribe()
        lifecycleScope.launchWhenCreated {
            viewModel.viewStates.onEach { viewState -> handleViewState(viewState) }.collect()
        }
    }

    private fun handleViewState(viewState: CurrencyDetailsViewState) {
        when {
            viewState.historyList != null -> {

            }

            viewState.error != null -> {
                handleError(viewState.error)
            }
        }
    }
}