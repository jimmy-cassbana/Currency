package com.jimmy.core_network.data.remote.model.response

data class ConvertionRateResponse(
    val base: String,
    val date: String,
    val rates: HashMap<String, Double>
)