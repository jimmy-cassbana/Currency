package com.jimmy.core_database.data.local.history

import com.jimmy.core_database.HistorySelection

interface HistoryLocalDataSource {
    suspend fun getHistory(): List<HistorySelection>?
    suspend fun saveHistory(history: HistorySelection): Long
    suspend fun deleteHistory()
}