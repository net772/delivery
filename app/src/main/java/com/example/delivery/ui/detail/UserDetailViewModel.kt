package com.example.delivery.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.delivery.data.entity.UserLikeEntity
import com.example.delivery.domain.usecase.GetUserLikeUseCase
import com.example.delivery.domain.usecase.ReqDeleteUserLikedUseCase
import com.example.delivery.domain.usecase.ReqInsertUserLikeUseCase
import com.example.delivery.state.ResultState
import com.example.delivery.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UserDetailViewModel(
    app: Application,
    private val userLikeEntity: UserLikeEntity,
    private val reqInsertUserLikeUseCase: ReqInsertUserLikeUseCase,
    private val getUserLikeUseCase: GetUserLikeUseCase,
    private val reqDeleteUserLikedUseCase: ReqDeleteUserLikedUseCase,
    ) : BaseViewModel(app) {

    companion object {
        const val KEY_USERDATA = "KEY_USERDATA"
    }

    private val _likeState = MutableLiveData<Boolean>()
    val likeState: LiveData<Boolean> get() = _likeState

    private val _userDetailStateLiveData = MutableStateFlow<ResultState<UserLikeEntity>>(ResultState.UnInitialize)
    val userDetailStateLiveData = _userDetailStateLiveData.asStateFlow()

    fun fetchData() = viewModelScope.launch {
        _userDetailStateLiveData.value = ResultState.Success(userLikeEntity)
        setLikeState(userLikeEntity.state)
    }

    fun setToggleLiked() = viewModelScope.launch {
        val userLikeEntityData = getUserLikeUseCase.invoke(userLikeEntity.id).firstOrNull()

        userLikeEntityData?.let {
            val userLikeEntity = userLikeEntity.copy(state = false)
            reqDeleteUserLikedUseCase.invoke(userLikeEntity.id)
            setLikeState(false)
        } ?: kotlin.run {
            val userLikeEntity = userLikeEntity.copy(state = true)
            reqInsertUserLikeUseCase.invoke(userLikeEntity)
            setLikeState(true)
        }
    }

    private fun setLikeState(state: Boolean) {
        _likeState.value = state
    }
}