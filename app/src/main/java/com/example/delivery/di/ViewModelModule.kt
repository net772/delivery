package com.example.delivery.di

import com.example.delivery.data.entity.UserLikeEntity
import com.example.delivery.ui.detail.UserDetailViewModel
import com.example.delivery.ui.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(androidApplication(), get(), get(), get()) }
    viewModel { (userLikeEntity: UserLikeEntity) -> UserDetailViewModel(androidApplication(), userLikeEntity, get(), get(), get()) }
}
