package com.vnmhpractice.scheduleapp

import com.vnmhpractice.scheduleapp.domain.domainModels.TokenStorage
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

actual val platformModule = module {
    single<HttpClientEngine> { Android.create() }
    single<TokenStorage> { TokenStorageImpl(androidContext()) }
}