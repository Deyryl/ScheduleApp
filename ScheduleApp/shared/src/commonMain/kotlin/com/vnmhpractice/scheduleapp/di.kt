package com.vnmhpractice.scheduleapp

import io.ktor.client.*
import org.koin.core.context.startKoin
import org.koin.dsl.module

//val dataModule = module {
//    single { HttpClient() }
//    single { ApiService(get()) }
//    single<UserRepository> { UserRepositoryImpl(get(), getOrNull()) }
//}
//
//val domainModule = module {
//    factory { GetUserByIdUseCase(get()) }
//    factory { GetUserByEmailUseCase(get()) }
//    factory { UpdateUserUseCase(get()) }
//}
//
//val presentationModule = module {
//    viewModel { UserViewModel(get(), get(), get()) }
//}
//
//fun initKoin() = startKoin {
//    modules(listOf(dataModule, domainModule, presentationModule))
//}