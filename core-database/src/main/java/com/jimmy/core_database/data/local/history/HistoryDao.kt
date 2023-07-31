package com.jimmy.core_database.data.local.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jimmy.core_database.HistorySelection

@Dao
interface HistoryDao {

    @Query("SELECT * from history")
    suspend fun getHistory(): List<HistorySelection>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveHistory(history: HistorySelection): Long

    @Query("DELETE FROM history")
    suspend fun deleteHistory()
}