package com.example.delivery.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.delivery.utility.config.KoinConstants
import com.example.delivery.state.ResultState
import com.example.delivery.utility.extension.collect
import com.example.delivery.utility.extension.onStartAndEndDelay
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

abstract class BaseViewModel(
    private val app: Application
): AndroidViewModel(app), KoinComponent {

    private val ioDispatcher: CoroutineDispatcher by inject(named(KoinConstants.DISPATCHER_IO))

    protected fun <T> Flow<T>.onResult(action: (T) -> Unit) {
        flowOn(ioDispatcher)
            .collect(viewModelScope, action)
    }

    protected inline fun <T> Flow<T>.onState(
        startDelay: Long? = null,
        crossinline collect: (ResultState<T>) -> Unit
    ) {
        viewModelScope.launch {
            onCompletion { collect(ResultState.Finish) }
                .onStartAndEndDelay(startDelay) { collect(ResultState.Loading) }
                .catch { collect(ResultState.Error(it)) }
                .collectLatest { collect(ResultState.Success(it)) }
        }
    }

    protected inline fun <T, F> Flow<T>.onStateAppend(
        arguments: F,
        startDelay: Long? = null,
        crossinline collect: (ResultState<Pair<T, F>>) -> Unit
    ) {
        viewModelScope.launch {
            onCompletion { collect(ResultState.Finish) }
                .onStartAndEndDelay(startDelay) { collect(ResultState.Loading) }
                .catch { collect(ResultState.Error(it)) }
                .collectLatest { collect(ResultState.Success(Pair(it, arguments))) }
        }
    }
}