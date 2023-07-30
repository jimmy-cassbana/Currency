package com.jimmy.core_arch.presentation.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    open fun init() = Unit
    open fun subscribe() = Unit


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        subscribe()
        handleToolBarBackButton()
    }

    private fun handleToolBarBackButton() {
        getActionBar()?.setDisplayHomeAsUpEnabled(true)
        getActionBar()?.setHomeButtonEnabled(true)
    }

    fun getActionBar() = (activity as? BaseActivity)?.supportActionBar

    fun setToolbar(toolbar: Toolbar) {
        (activity as? BaseActivity)?.setSupportActionBar(toolbar)
    }


    fun setScreenTitle(text: String) {
        (activity as? BaseActivity)?.supportActionBar?.title = text
    }
}