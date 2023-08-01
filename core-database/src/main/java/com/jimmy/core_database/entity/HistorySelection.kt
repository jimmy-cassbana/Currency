package com.jimmy.core_database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "history",
    indices = [Index(
        value = ["date", "fromCurrency", "toCurrency", "fromValue", "toValue"],
        unique = true
    )]
)
data class HistorySelection(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val date: String,
    val fromCurrency: String,
    val toCurrency: String,
    val fromValue: String,
    val toValue: String,
)
