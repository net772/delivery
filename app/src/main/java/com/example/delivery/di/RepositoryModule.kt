package com.example.delivery.di

import com.example.delivery.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single<MapRepository> {  MapRepositoryImpl(get() ) }
    single<DbRepository> {  DbRepositoryImpl(get() ) }
    single<UserRepository> {  UserRepositoryImpl(get() ) }
}