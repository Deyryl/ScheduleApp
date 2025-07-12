package com.vnmhpractice.scheduleapp

import io.ktor.client.engine.okhttp.OkHttp
import com.vnmhpractice.scheduleapp.domain.domainModels.TokenStorage
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

actual val platformModule = module {
    single<HttpClientEngine> { OkHttp.create() }
    single<TokenStorage> { TokenStorageImpl(androidContext()) }
}