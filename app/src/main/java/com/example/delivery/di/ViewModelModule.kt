package com.example.delivery.di

import com.example.delivery.ui.home.HomeViewModel
import com.example.delivery.ui.home.restaurant.RestaurantListViewModel
import com.example.delivery.ui.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(androidApplication()) }
    viewModel { HomeViewModel(androidApplication(), get(), get()) }
    viewModel { RestaurantListViewModel(androidApplication()) }

}