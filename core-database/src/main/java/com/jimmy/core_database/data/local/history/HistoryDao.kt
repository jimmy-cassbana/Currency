package com.jimmy.core_database.data.local.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jimmy.core_database.entity.HistorySelection

@Dao
interface HistoryDao {

    @Query("SELECT * from history WHERE date > (SELECT DATETIME('now', '-3 day'))")
    suspend fun getHistory(): List<HistorySelection>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveHistory(selection: HistorySelection): Long

    @Query("DELETE FROM history")
    suspend fun deleteHistory()
}