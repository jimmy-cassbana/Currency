package com.jimmy.core_data.data.repository

import com.jimmy.core_data.domain.repository.CurrencyRepository
import com.jimmy.core_network.data.remote.module.currency.CurrencyRemoteDataSource
import javax.inject.Inject

class CurrencyRepositoryImp @Inject constructor(
    private val remoteDataSource: CurrencyRemoteDataSource
) : CurrencyRepository {
    override suspend fun convertCurrency(base: String) = remoteDataSource.convertCurrency(base)
}