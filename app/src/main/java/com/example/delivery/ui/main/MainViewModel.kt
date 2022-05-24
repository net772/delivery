package com.example.delivery.ui.main

import android.app.Application
import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.delivery.R
import com.example.delivery.data.entity.UserLikeEntity
import com.example.delivery.domain.usecase.GetGithubUseCase
import com.example.delivery.domain.usecase.GetUserAllLikeUseCase
import com.example.delivery.domain.usecase.GetUserLikeUseCase
import com.example.delivery.state.ResultState
import com.example.delivery.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    app: Application,
    private val getGithubUseCase: GetGithubUseCase,
    private val getUserAllLikeUseCase: GetUserAllLikeUseCase,
    private val getUserLikeUseCase: GetUserLikeUseCase,
) : BaseViewModel(app) {

    private val _mainTabMenuFlow = MutableSharedFlow<MainTabMenu>()
    val mainTabMenuFlow = _mainTabMenuFlow.asSharedFlow()

    suspend fun changeMenu(event: MainTabMenu) {
        _mainTabMenuFlow.emit(event)
    }

}