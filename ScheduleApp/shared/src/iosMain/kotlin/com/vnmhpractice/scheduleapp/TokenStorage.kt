package com.vnmhpractice.scheduleapp
import com.vnmhpractice.scheduleapp.domain.domainModels.TokenStorage
import com.vnmhpractice.scheduleapp.domain.domainModels.TokenPair
import kotlinx.serialization.Serializable


class TokenStorageImpl(): TokenStorage {
    override suspend fun load(): TokenPair? { return null }//Достать токены
    override suspend fun save(tokenPair: TokenPair) {}//Загрузить токены в память
    override suspend fun clear() {}//Очистить токены из памяти
}