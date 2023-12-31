package com.jimmy.core_database.data.local.history

import com.jimmy.core_database.entity.HistorySelection

interface HistoryLocalDataSource {
    suspend fun getHistory(): List<HistorySelection>?
    suspend fun saveHistory(selection: HistorySelection): Long
    suspend fun deleteHistory()
}