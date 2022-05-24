package com.example.delivery.di

import com.example.delivery.data.repository.ApiRepository
import com.example.delivery.data.repository.ApiRepositoryImpl
import com.example.delivery.data.repository.DbRepository
import com.example.delivery.data.repository.DbRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ApiRepository> {  ApiRepositoryImpl(get() ) }
    single<DbRepository> {  DbRepositoryImpl(get() ) }
}