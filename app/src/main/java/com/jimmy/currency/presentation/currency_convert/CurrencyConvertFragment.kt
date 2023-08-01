package com.jimmy.currency.presentation.currency_convert

import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jimmy.core_database.entity.HistorySelection
import com.jimmy.currency.R
import com.jimmy.currency.base.AppBaseFragment
import com.jimmy.currency.databinding.FragmentCurrencyConvertBinding
import com.leo.searchablespinner.SearchableSpinner
import com.leo.searchablespinner.interfaces.OnItemSelectListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CurrencyConvertFragment :
    AppBaseFragment<FragmentCurrencyConvertBinding>(FragmentCurrencyConvertBinding::inflate) {

    private val viewModel: CurrencyConvertViewModel by viewModels()
    private var currencyMap = HashMap<String, Double>()
    private var currencySpinner: SearchableSpinner? = null
    private var selectedEditText: EditText? = null
    private var date: String = ""

    override fun init() {
        super.init()
        viewModel.executeAction(
            CurrencyConvertAction
                .GetConversionRates(getString(R.string.eur_key))
        )
    }

    override fun onResume() {
        super.onResume()
        initListeners()
    }

    private fun initListeners() {
        binding.apply {
            spinnerFrom.editText?.let { addSpinnerAction(it) }
            spinnerFrom.editText?.doAfterTextChanged { convertCurrency(date) }

            spinnerTo.editText?.let { addSpinnerAction(it) }
            spinnerTo.editText?.doAfterTextChanged { convertCurrency(date) }

            etResult.keyListener = null
            etInput.doAfterTextChanged { convertCurrency(date) }
        }
        initClickActions()
    }

    private fun initClickActions() {
        binding.apply {
            ibSwap.setOnClickListener {
                val temp = spinnerFrom.editText?.text
                spinnerFrom.editText?.text = binding.spinnerTo.editText?.text
                spinnerTo.editText?.text = temp
            }

            btnDetails.setOnClickListener {
                findNavController().navigate(
                    CurrencyConvertFragmentDirections
                        .actionCurrencyConvertFragmentToCurrencyDetailsFragment(
                            getBaseCurrency(),
                            getBaseValue().toFloat(),
                            viewModel.getPopularCurrencies(currencyMap)
                        )
                )
            }
        }
    }

    private fun convertCurrency(date: String) {
        if (binding.etInput.text.toString().isEmpty())
            return
        val amount = binding.etInput.text.toString().toDouble()
        val result = viewModel.convertCurrency(getBaseValue(), getToValue(), amount)
        viewModel.executeAction(
            CurrencyConvertAction.SaveHistorySelection(
                HistorySelection(
                    date = date,
                    fromCurrency = getBaseCurrency(),
                    toCurrency = getToCurrency(),
                    fromValue = amount.toString(),
                    toValue = result.toString()
                )
            )
        )
        binding.etResult.setText(result.toString())
    }

    private fun initSpinners() {
        val currencyKeys = arrayListOf<String>()
        currencyMap.forEach { currencyKeys.add(it.key) }
        currencySpinner = SearchableSpinner(requireContext())
        currencySpinner?.setSpinnerListItems(currencyKeys)
        currencySpinner?.onItemSelectListener = object : OnItemSelectListener {
            override fun setOnItemSelectListener(position: Int, selectedString: String) {
                selectedEditText?.setText(selectedString)
            }
        }
        initListeners()
    }

    private fun addSpinnerAction(editText: EditText) {
        editText.keyListener = null
        editText.setOnClickListener {
            selectedEditText = editText
            currencySpinner?.show()
        }
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
                date = viewState.result.date
                currencyMap = viewState.result.rates
                if (currencySpinner == null) initSpinners()
            }
            viewState.error != null -> {
                handleError(viewState.error)
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun getBaseCurrency() = binding.spinnerFrom.editText?.text.toString()
    private fun getToCurrency() = binding.spinnerTo.editText?.text.toString()
    private fun getBaseValue() = currencyMap[getBaseCurrency()] ?: 0.0
    private fun getToValue() = currencyMap[getToCurrency()] ?: 0.0

}