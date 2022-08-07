package com.example.delivery.ui.home

import androidx.annotation.StringRes
import com.example.delivery.data.entity.location.MapSearchInfoEntity

sealed class HomeState {

    object Uninitialized: HomeState()

    object Loading: HomeState()

    data class Success(
        val mapSearchInfoEntity: MapSearchInfoEntity,
        val isLocationSame: Boolean,
    ): HomeState()

    data class Error(
        @StringRes val messageId: Int
    ): HomeState()

    object Finish: HomeState()
}