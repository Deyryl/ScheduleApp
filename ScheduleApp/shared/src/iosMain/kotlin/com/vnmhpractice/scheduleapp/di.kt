package com.vnmhpractice.scheduleapp

import com.vnmhpractice.scheduleapp.domain.domainModels.TokenStorage
import io.ktor.client.engine.*
import org.koin.dsl.module

actual val platformModule = module {
    single<TokenStorage> { TokenStorageImpl() }
    single<HttpClientEngine> { Ios.create() }
}