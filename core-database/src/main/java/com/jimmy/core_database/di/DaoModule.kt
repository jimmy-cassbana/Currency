package com.jimmy.core_database.di

import com.jimmy.core_database.data.local.history.HistoryDao
import com.jimmy.core_database.data.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    fun providesAuthDao(appDatabase: AppDatabase): HistoryDao = appDatabase.historyDao()

}