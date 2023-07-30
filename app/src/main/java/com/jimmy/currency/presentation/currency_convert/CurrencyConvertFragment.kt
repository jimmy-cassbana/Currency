package com.jimmy.currency.presentation.currency_convert

import android.view.View
import android.widget.EditText
import android.widget.Toast
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
    private val currencyKeys = arrayListOf<String>()
    private var currencySpinner: SearchableSpinner? = null
    private var selectedEditText: EditText? = null

    override fun init() {
        super.init()
        initViews()
        viewModel.executeAction(CurrencyConvertAction.GetConversionRates("EUR"))
    }

    private fun initViews() {
        binding.etResult.keyListener = null
    }

    private fun initSpinners() {
        currencySpinner = SearchableSpinner(requireContext())
        currencySpinner?.setSpinnerListItems(currencyKeys)
        currencySpinner?.onItemSelectListener = object : OnItemSelectListener {
            override fun setOnItemSelectListener(position: Int, selectedString: String) {
                selectedEditText?.setText(selectedString)
            }
        }
        binding.spinnerFrom.editText?.let { addSpinnerAction(it) }
        binding.spinnerTo.editText?.let { addSpinnerAction(it) }
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
                viewState.result.rates.forEach {
                    currencyKeys.add(it.key)
                    initSpinners()
                }
            }
            viewState.error != null -> {
                Toast.makeText(requireContext(), viewState.error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }
}