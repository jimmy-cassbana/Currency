package com.jimmy.core_database

data class HistorySelection(
    val date: String,
    val fromCurrency: String,
    val toCurrency: String,
    val fromValue: String,
    val toValue: String
)
