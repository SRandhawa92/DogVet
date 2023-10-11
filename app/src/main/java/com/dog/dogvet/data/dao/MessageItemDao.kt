package com.dog.dogvet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dog.dogvet.data.entity.MessageItem

@Dao
interface MessageItemDao {
    @Insert
    fun insertAll(vararg items: MessageItem)

    @Delete
    fun delete(item: MessageItem)

    @Query("SELECT DISTINCT * FROM message_item")
    fun getAll(): List<MessageItem>
}
