package com.dog.dogvet.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dog.dogvet.data.dao.MessageItemDao
import com.dog.dogvet.data.entity.MessageItem

@Database(entities = [MessageItem::class], version = 1)
abstract class MessagesDatabase : RoomDatabase() {
    abstract fun messagesDao(): MessageItemDao
}
