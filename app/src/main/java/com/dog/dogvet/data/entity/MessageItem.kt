package com.dog.dogvet.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_item")
data class MessageItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "is_bot") val isBot: Boolean,
    @ColumnInfo(name = "data") val data: String,
    @ColumnInfo(name = "timestamp") val timestamp: String,
)
