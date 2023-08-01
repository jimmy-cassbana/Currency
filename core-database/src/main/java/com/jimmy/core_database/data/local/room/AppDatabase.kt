package com.jimmy.core_database.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jimmy.core_database.entity.HistorySelection
import com.jimmy.core_database.data.local.history.HistoryDao
import com.jimmy.core_database.data.local.room.AppDatabase.Companion.DATABASE_VERSION

@Database(
    entities = [HistorySelection::class],
    version = DATABASE_VERSION,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "currency"
        const val DATABASE_VERSION = 1
    }

    abstract fun historyDao(): HistoryDao

}