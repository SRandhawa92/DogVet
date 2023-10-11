package com.dog.dogvet.api

import android.util.Log
import com.dog.dogvet.data.openai.Config
import com.dog.dogvet.data.openai.GeneratedAnswer
import com.dog.dogvet.data.openai.Message
import com.dog.dogvet.data.openai.RequestBody
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiService {

    private var client: HttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                },
            )
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 30000
        }

        install(Logging) {
            logger = object : Logger { override fun log(message: String) { Log.d("API", message) } }
            level = LogLevel.ALL
        }
    }

    suspend fun completion(msg: String): String {
        val chat = Message(role = "user", content = msg)
        val resp = client.post {
            url("${ApiConstants.URL}${Endpoints.COMPLETIONS}")
            contentType(ContentType.Application.Json)
            header("Authorization", "Bearer ${ApiConstants.TOKEN}")
            setBody(
                RequestBody(
                    model = Config.AI_ENGINE,
                    max_tokens = Config.MAX_TOKENS,
                    messages = listOf(Config.promptMsg, chat),
                ),
            )
        }

        return when (resp.status.value) {
            200 -> resp.body<GeneratedAnswer>().choices[0].message.content ?: ErrorConstants.REPLY
            else -> resp.status.description
        }
    }
}
