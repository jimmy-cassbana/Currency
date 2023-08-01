package com.jimmy.core_data.data.repository

import com.jimmy.core_data.domain.repository.HistoryRepository
import com.jimmy.core_database.entity.HistorySelection
import com.jimmy.core_database.data.local.history.HistoryLocalDataSource
import javax.inject.Inject

class HistoryRepositoryImp @Inject constructor(
    private val localDataSource: HistoryLocalDataSource
) : HistoryRepository {

    override suspend fun getHistory(): List<HistorySelection>? {
        return localDataSource.getHistory()
    }

    override suspend fun saveHistory(selection: HistorySelection): Long {
        return localDataSource.saveHistory(selection)
    }

    override suspend fun deleteHistory() {
        return localDataSource.deleteHistory()
    }

}