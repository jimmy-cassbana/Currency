package com.jimmy.core_database.data.local.history

import com.jimmy.core_arch.CoroutineDispatchers
import com.jimmy.core_database.HistorySelection
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HistoryLocalDataSourceImp @Inject constructor(
    private val dao: HistoryDao,
    private val coroutineDispatchers: CoroutineDispatchers
) : HistoryLocalDataSource {
    override suspend fun getHistory(): List<HistorySelection>? {
        return withContext(coroutineDispatchers.io) {
            dao.getHistory()
        }
    }

    override suspend fun saveHistory(history: HistorySelection): Long {
        return withContext(coroutineDispatchers.io) {
            dao.saveHistory(history)
        }
    }

    override suspend fun deleteHistory() {
        return withContext(coroutineDispatchers.io) {
            dao.deleteHistory()
        }
    }
}