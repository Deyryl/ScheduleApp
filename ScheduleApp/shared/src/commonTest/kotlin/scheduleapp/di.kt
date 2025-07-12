package com.vnmhpractice.scheduleapp

import com.vnmhpractice.scheduleapp.data.RepositoryImplementations.UserRepositoryImpl
import com.vnmhpractice.scheduleapp.data.networking.ApiService
import com.vnmhpractice.scheduleapp.data.networking.ApiServiceImpl
import com.vnmhpractice.scheduleapp.data.networking.createHttpClient
import com.vnmhpractice.scheduleapp.domain.domainModels.TokenStorage
import com.vnmhpractice.scheduleapp.domain.repositories.UserRepository
import com.vnmhpractice.scheduleapp.domain.useCases.GetUserByEmailUseCase
import com.vnmhpractice.scheduleapp.domain.useCases.GetUserByIdUseCase
import com.vnmhpractice.scheduleapp.domain.useCases.UpdateUserUseCase
import io.ktor.client.*
import io.ktor.client.engine.*
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    //data
    single<HttpClientEngine> { get() }

    single<TokenStorage> { error("No TokenStorage bound for this platform") }

    single<HttpClient> {
        createHttpClient(
            engine = get(),
            tokenStorage = get()
        )
    }
    single<ApiService> { ApiServiceImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }

    //domain
    factory { GetUserByIdUseCase(get()) }
    factory { GetUserByEmailUseCase(get()) }
    factory { UpdateUserUseCase(get()) }
}

expect val platformModule: Module

fun initKoin() = startKoin {
    modules(commonModule, platformModule)
}