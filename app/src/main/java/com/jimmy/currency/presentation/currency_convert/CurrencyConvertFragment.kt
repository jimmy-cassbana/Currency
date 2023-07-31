package com.jimmy.currency.presentation.currency_convert

import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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

    override fun init() {
        super.init()
        viewModel.executeAction(CurrencyConvertAction.GetConversionRates(EUR_KEY))
    }

    private fun initListeners() {
        binding.etResult.keyListener = null
        binding.etInput.doAfterTextChanged { convertCurrency() }
        binding.spinnerTo.editText?.doAfterTextChanged { convertCurrency() }
        binding.ibSwap.setOnClickListener {
            val temp = binding.spinnerFrom.editText?.text
            binding.spinnerFrom.editText?.text = binding.spinnerTo.editText?.text
            binding.spinnerTo.editText?.text = temp
        }
        binding.spinnerFrom.editText?.doAfterTextChanged {
            viewModel.executeAction(CurrencyConvertAction.GetConversionRates(it.toString()))
        }
    }

    private fun convertCurrency() {
        if (binding.etInput.text.toString().isEmpty()) return
        val currency = binding.spinnerTo.editText?.text.toString()
        val amount = binding.etInput.text.toString().toDouble()
        currencyMap[currency]?.let { rate ->
            binding.etResult.setText((amount * rate).toString())
        }
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
        binding.spinnerFrom.editText?.let { addSpinnerAction(it) }
        binding.spinnerTo.editText?.let { addSpinnerAction(it) }
        initListeners()
    }

    private fun addSpinnerAction(editText: EditText) {
        editText.keyListener = null
        editText.setOnClickListener {
            selectedEditText = editText
            currencySpinner?.show()
        }
        editText.setText(EUR_KEY)
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

    companion object {
        const val EUR_KEY = "EUR"
    }
}