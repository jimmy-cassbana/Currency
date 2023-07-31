package com.jimmy.core_database.di

import com.jimmy.core_arch.CoroutineDispatchers
import com.jimmy.core_database.data.local.history.HistoryDao
import com.jimmy.core_database.data.local.history.HistoryLocalDataSource
import com.jimmy.core_database.data.local.history.HistoryLocalDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {
    @Singleton
    @Provides
    fun providesUserLocalDataSource(
        dao: HistoryDao,
        coroutineDispatchers: CoroutineDispatchers
    ): HistoryLocalDataSource = HistoryLocalDataSourceImp(dao, coroutineDispatchers)

}