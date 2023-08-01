package com.jimmy.currency.util

import android.view.View
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double.roundTwo(): Double {
    val newFormat = DecimalFormat("#.##", DecimalFormatSymbols(Locale.ENGLISH))
    return java.lang.Double.valueOf(newFormat.format(this))
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}