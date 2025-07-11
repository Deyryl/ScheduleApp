package com.vnmhpractice.scheduleapp.data.dtoClasses
import kotlinx.serialization.Serializable

@Serializable
data class TokenPair(
    val accessToken: String,
    val refreshToken: String
)

interface TokenStorage {
    suspend fun load(): TokenPair? //Достать токены
    suspend fun save(tokens: TokenPair) //Загрузить токены в память
    suspend fun clear() //Очистить токены из памяти
}