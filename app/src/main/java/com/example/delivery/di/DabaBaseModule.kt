package com.example.delivery.di

import com.example.delivery.data.db.provideDB
import com.example.delivery.data.db.provideLocationDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataBaseModule = module {
    single { provideDB(androidApplication()) }
    single { provideLocationDao(get()) }
}
