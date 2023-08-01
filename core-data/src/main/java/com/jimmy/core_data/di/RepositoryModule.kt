package com.jimmy.core_data.di

import com.jimmy.core_data.data.repository.CurrencyRepositoryImp
import com.jimmy.core_data.data.repository.HistoryRepositoryImp
import com.jimmy.core_data.domain.repository.CurrencyRepository
import com.jimmy.core_data.domain.repository.HistoryRepository
import com.jimmy.core_database.data.local.history.HistoryLocalDataSource
import com.jimmy.core_network.data.remote.module.currency.CurrencyRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesCurrencyRepository(
        remoteDataSource: CurrencyRemoteDataSource,
    ): CurrencyRepository {
        return CurrencyRepositoryImp(remoteDataSource)
    }

    @Provides
    @Singleton
    fun providesHistoryRepository(
        localDataSource: HistoryLocalDataSource,
    ): HistoryRepository {
        return HistoryRepositoryImp(localDataSource)
    }
}
