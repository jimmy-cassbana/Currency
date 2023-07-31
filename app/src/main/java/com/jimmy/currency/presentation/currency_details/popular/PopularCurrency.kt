package com.jimmy.currency.presentation.currency_details.popular

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularCurrency(val key: String, val value: Double) : Parcelable
