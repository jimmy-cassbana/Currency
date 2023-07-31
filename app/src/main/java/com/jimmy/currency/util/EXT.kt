package com.jimmy.currency.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double.roundTwo(): Double {
    val newFormat = DecimalFormat("#.##", DecimalFormatSymbols(Locale.ENGLISH))
    return java.lang.Double.valueOf(newFormat.format(this))
}
