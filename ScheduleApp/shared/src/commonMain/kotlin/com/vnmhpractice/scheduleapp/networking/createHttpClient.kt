package com.vnmhpractice.scheduleapp.networking

import com.vnmhpractice.scheduleapp.models.TokenPair
import io.ktor.client.*
import io.ktor.http.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import com.vnmhpractice.scheduleapp.models.TokenStorage
import io.ktor.client.request.*
import io.ktor.http.ContentType.Application.Json

fun createHttpClient(engine: HttpClientEngine, tokenStorage: TokenStorage): HttpClient {
    return HttpClient(engine) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Auth) {
            bearer {
                loadTokens {
                    tokenStorage.load()?.let {
                        BearerTokens(it.accessToken, it.refreshToken)
                    }
                }

                refreshTokens {
                    val response = try {
                        val currentRefreshToken = tokenStorage.load()?.refreshToken ?: return@refreshTokens null

                        val tokenPair = client.post(ApiRoutes.Auth.REFRESH) {
                            contentType(ContentType.Application.Json)
                            setBody(RefreshRequest(refreshToken = currentRefreshToken))
                        }.body<TokenPair>()

                        tokenStorage.save(tokenPair)
                        BearerTokens(
                            accessToken = tokenPair.accessToken,
                            refreshToken = tokenPair.refreshToken
                        )
                    } catch (e: Exception) {
                        println("Invalid token")
                        null
                    }

                    response
                }
            }
        }
    }
}