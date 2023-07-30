package com.jimmy.currency.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.jimmy.core_arch.presentation.view.BaseFragment

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

}