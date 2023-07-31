package com.jimmy.currency.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.jimmy.core_arch.presentation.view.BaseFragment
import com.jimmy.core_network.ext.getCustomException
import com.jimmy.core_network.R

abstract class AppBaseFragment<VB : ViewBinding>(private val bindingFactory: BindingFactory<VB>) :
    BaseFragment() {

    lateinit var binding: VB
    var hasInitializedRootView = false
    private var rootView: View? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingFactory(layoutInflater, container, false)
        return binding.root
    }

    fun handleError(
        throwable: Throwable,
        retryAction: (() -> Unit)? = null
    ) {
        throwable.printStackTrace()
        val customError = throwable.getCustomException(requireContext())
        view?.let {
            val message = customError.message ?: customError.localizedMessage ?: getString(
                R.string.unknown_error_message
            )
            val snackBar = Snackbar.make(it, message, Snackbar.LENGTH_SHORT)
            retryAction?.let {
                snackBar.setAction(getString(R.string.retry)) { retryAction() }
            }
            snackBar.view.apply {
                setBackgroundResource(android.R.color.black)
            }
            snackBar.show()
        }
    }

}