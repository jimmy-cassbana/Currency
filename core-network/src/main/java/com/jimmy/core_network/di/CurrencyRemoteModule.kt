package com.jimmy.core_network.di

import com.jimmy.core_arch.CoroutineDispatchers
import com.jimmy.core_network.data.remote.module.currency.CurrencyApi
import com.jimmy.core_network.data.remote.module.currency.CurrencyRemoteDataSource
import com.jimmy.core_network.data.remote.module.currency.CurrencyRemoteDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrencyRemoteModule {

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): CurrencyApi {
        return retrofit.create(CurrencyApi::class.java)
    }

    @Singleton
    @Provides
    fun providesRemoteDataSource(
        api: CurrencyApi,
        coroutineDispatchers: CoroutineDispatchers
    ): CurrencyRemoteDataSource {
        return CurrencyRemoteDataSourceImp(api, coroutineDispatchers)
    }
}