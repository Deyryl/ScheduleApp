package com.vnmhpractice.scheduleapp

import com.vnmhpractice.scheduleapp.domain.domainModels.TokenStorage
import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*
import org.koin.dsl.module

actual val platformModule = module {
    single<TokenStorage> { TokenStorageImpl() }
    single<HttpClientEngine> { Darwin.create() }
}