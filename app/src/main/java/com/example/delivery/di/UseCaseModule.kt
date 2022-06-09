package com.example.delivery.di

import com.example.delivery.domain.usecase.GetReverseGeoInformationUseCase
import com.example.delivery.domain.usecase.GetUserLocationUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetReverseGeoInformationUseCase(get()) }
    factory { GetUserLocationUseCase(get()) }
}