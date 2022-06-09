package com.example.delivery.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.delivery.utility.config.KoinConstants
import com.example.delivery.state.ResultState
import com.example.delivery.utility.extension.collect
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

abstract class BaseViewModel(
    private val app: Application
): AndroidViewModel(app), KoinComponent {

    private val ioDispatcher: CoroutineDispatcher by inject(named(KoinConstants.DISPATCHER_IO))

    protected fun <T> Flow<T>.onResult(action: (T) -> Unit) {
        flowOn(ioDispatcher).collect(viewModelScope, action)
    }

    protected fun <T> Flow<T>.onState(collect: (ResultState<T>) -> Unit) {
        flowOn(ioDispatcher)
            .onCompletion { collect(ResultState.Finish) }
            .onStart { collect(ResultState.Loading) }
            .catch { collect(ResultState.Error(it)) }
            .onEach { collect(ResultState.Success(it)) }
            .launchIn(viewModelScope)
    }
}