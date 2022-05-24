package com.example.delivery

import android.app.Application
import com.example.delivery.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Application)
            modules(
                coroutineModule,
                networkModule,
                useCaseModule,
                viewModelModule,
                repositoryModule,
                dataBaseModule
            )
        }
    }
}