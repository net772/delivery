package com.example.delivery.utility.event

import android.util.Log
import com.example.delivery.utility.config.KoinConstants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

object EventBus : KoinComponent {

    private val defaultDispatcher: CoroutineDispatcher by inject(named(KoinConstants.DISPATCHER_DEFAULT))

    private val emitter = MutableSharedFlow<Event>()
    val collector = emitter.asSharedFlow()

    fun emit(event: Event, externalScope: CoroutineScope = GlobalScope) {
        externalScope.launch(defaultDispatcher) {
            emitter.emit(event)
        }
    }

    inline fun <reified T : Event> collect(
        externalScope: CoroutineScope,
        crossinline collect: (T) -> Unit
    ) {
        collector
            .filter { it is T }
            .map { it as T }
            .onEach { collect.invoke(it) }
            .launchIn(externalScope)
    }

    interface Event

}