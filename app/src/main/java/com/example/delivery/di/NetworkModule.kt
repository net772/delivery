package com.example.delivery.di

import com.example.delivery.data.network.buildOkHttpClient
import com.example.delivery.data.network.provideGithupApiService
import com.example.delivery.data.network.provideGithupRetrofit
import com.example.delivery.data.network.provideGsonConverterFactory
import org.koin.dsl.module

val networkModule = module {
    single { provideGsonConverterFactory() }

    single { buildOkHttpClient() }

    single { provideGithupRetrofit(get(), get()) }

    single { provideGithupApiService(get()) }
}
