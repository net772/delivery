package com.example.delivery.di

import com.example.delivery.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetGithubUseCase(get()) }
    factory { GetUserAllLikeUseCase(get()) }
    factory { ReqInsertUserLikeUseCase(get()) }
    factory { ReqDeleteUserLikedUseCase(get()) }
    factory { GetUserLikeUseCase(get()) }
}