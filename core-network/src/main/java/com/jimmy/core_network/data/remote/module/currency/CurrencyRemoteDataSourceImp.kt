package com.jimmy.core_network.data.remote.module.currency

import com.jimmy.core_arch.CoroutineDispatchers
import com.jimmy.core_arch.domain.DataState
import com.jimmy.core_network.data.remote.model.response.ConvertionRateResponse
import com.jimmy.core_network.ext.getDataState
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRemoteDataSourceImp @Inject constructor(
    private val api: CurrencyApi,
    private val coroutineDispatchers: CoroutineDispatchers
) :CurrencyRemoteDataSource {
    override suspend fun convertCurrency(base: String): DataState<ConvertionRateResponse> =
        withContext(coroutineDispatchers.io) {
            api.convertCurrency(base).getDataState()
        }

}
