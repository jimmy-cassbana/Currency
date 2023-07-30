package com.jimmy.currency.presentation.currency_convert

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.jimmy.currency.base.AppBaseFragment
import com.jimmy.currency.databinding.FragmentCurrencyConvertBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CurrencyConvertFragment :
    AppBaseFragment<FragmentCurrencyConvertBinding>(FragmentCurrencyConvertBinding::inflate) {

    private val viewModel: CurrencyConvertViewModel by viewModels()

    override fun init() {
        super.init()
        viewModel.executeAction(CurrencyConvertAction.GetConversionRates("EUR"))
    }

    override fun subscribe() {
        super.subscribe()
        lifecycleScope.launchWhenCreated {
            viewModel.viewStates.onEach { viewState -> handleViewState(viewState) }.collect()
        }
    }

    private fun handleViewState(viewState: CurrencyConvertViewState) {
        setLoading(viewState.loading)
        when {
            viewState.result != null -> {
                Toast.makeText(requireContext(), viewState.result.date, Toast.LENGTH_SHORT).show()
            }

            viewState.error != null -> {
                Toast.makeText(requireContext(), viewState.error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setLoading(loading: Boolean) {
    }
}