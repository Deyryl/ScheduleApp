package com.vnmhpractice.scheduleapp.models

import com.vnmhpractice.scheduleapp.networking.TokenPair

interface TokenStorage {
    suspend fun load(): TokenPair?
    suspend fun save(tokens: TokenPair)
    suspend fun clear()
}