package com.jimmy.currency.network.model.response

data class ConvertionRateResponse(
    val base: String,
    val date: String,
    val rates: HashMap<String, Double>
)