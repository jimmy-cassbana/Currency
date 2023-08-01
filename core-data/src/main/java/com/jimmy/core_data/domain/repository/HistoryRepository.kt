package com.jimmy.core_data.domain.repository

import com.jimmy.core_database.entity.HistorySelection

interface HistoryRepository {
    suspend fun getHistory(): List<HistorySelection>?
    suspend fun saveHistory(selection: HistorySelection): Long
    suspend fun deleteHistory()
}