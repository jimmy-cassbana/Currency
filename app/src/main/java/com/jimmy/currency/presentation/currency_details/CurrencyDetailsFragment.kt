package com.jimmy.currency.presentation.currency_details

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jimmy.currency.base.AppBaseFragment
import com.jimmy.currency.databinding.FragmentCurrencyDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

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
    }
}