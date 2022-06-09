package com.example.delivery.ui.main

import android.app.Application
import com.example.delivery.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MainViewModel(
    app: Application
) : BaseViewModel(app) {

    private val _mainTabMenuFlow = MutableSharedFlow<MainTabMenu>()
    val mainTabMenuFlow = _mainTabMenuFlow.asSharedFlow()

    suspend fun changeMenu(event: MainTabMenu) {
        _mainTabMenuFlow.emit(event)
    }

}