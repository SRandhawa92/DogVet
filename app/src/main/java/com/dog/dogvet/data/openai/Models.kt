package com.dog.dogvet.data.openai

@kotlinx.serialization.Serializable
data class RequestBody(
    val model: String,
    val messages: List<Message?>,
    val max_tokens: Int,
)

@kotlinx.serialization.Serializable
data class Message(
    val role: String?,
    val content: String?,
)

@kotlinx.serialization.Serializable
data class GeneratedAnswer(
    val choices: List<Choice>,
    val created: Int,
    val id: String,
    val model: String,
    val `object`: String,
    val usage: Usage,
)

@kotlinx.serialization.Serializable
data class Choice(
    val finish_reason: String,
    val index: Int,
    val message: Message,
)

@kotlinx.serialization.Serializable
data class Usage(
    val completion_tokens: Int,
    val prompt_tokens: Int,
    val total_tokens: Int,
)
