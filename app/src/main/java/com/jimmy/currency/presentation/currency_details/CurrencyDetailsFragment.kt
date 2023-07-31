package com.jimmy.currency.presentation.currency_details

import androidx.navigation.fragment.navArgs
import com.jimmy.currency.base.AppBaseFragment
import com.jimmy.currency.databinding.FragmentCurrencyDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyDetailsFragment :
    AppBaseFragment<FragmentCurrencyDetailsBinding>(FragmentCurrencyDetailsBinding::inflate) {

    private val args: CurrencyDetailsFragmentArgs by navArgs()

    override fun init() {
        super.init()
    }
}