package com.example.delivery.di

import com.example.delivery.data.network.buildOkHttpClient
import com.example.delivery.data.network.provideGsonConverterFactory
import com.example.delivery.data.network.provideMapApiService
import com.example.delivery.data.network.provideMapRetrofit
import org.koin.dsl.module

val networkModule = module {
    single { provideGsonConverterFactory() }

    single { buildOkHttpClient() }

    single { provideMapApiService(get()) }

    single { provideMapRetrofit(get(), get()) }
}
