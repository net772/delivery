package com.example.delivery.utility.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

inline fun <T> Flow<T>.collect(
    externalScope: CoroutineScope,
    crossinline collect: (T) -> Unit
) = onEach { collect.invoke(it) }.launchIn(externalScope)

fun Flow<Unit>.collect(
    externalScope: CoroutineScope,
    collect: () -> Unit
) = onEach { collect.invoke() }.launchIn(externalScope)

fun <T> Flow<T>.onStartAndEndDelay(
    delay: Long? = null,
    action: suspend () -> Unit
): Flow<T> = onStart {
    action.invoke()
    if (delay != null) delay(delay)
}