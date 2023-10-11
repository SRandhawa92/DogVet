package com.dog.dogvet.data

import com.dog.dogvet.api.ApiService
import com.dog.dogvet.api.ErrorConstants
import com.dog.dogvet.data.entity.MessageItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MessagesRepository @Inject constructor(
    private val database: MessagesDatabase,
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineContext,
) {

    suspend fun getConversation(): Flow<List<MessageItem>> = flow {
        emit(database.messagesDao().getAll())
    }.flowOn(ioDispatcher)

    suspend fun addMsg(msg: String): Flow<List<MessageItem>> = flow {
        val msgItem = MessageItem(isBot = false, data = msg, timestamp = "3:12")
        database.messagesDao().insertAll(msgItem)
        emit(database.messagesDao().getAll())
    }.flowOn(ioDispatcher)

    suspend fun getReply(msg: String): Flow<List<MessageItem>> = flow {
        val replyMsg = try {
            apiService.completion(msg)
        } catch (e: Exception) {
            ErrorConstants.REPLY
        }
        val replyMsgItem = MessageItem(isBot = true, data = replyMsg, timestamp = "3:12")
        database.messagesDao().insertAll(replyMsgItem)
        emit(database.messagesDao().getAll())
    }.flowOn(ioDispatcher)
}
