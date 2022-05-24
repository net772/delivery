package com.example.delivery.utility.extension

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.delivery.utility.event.EventBus
import kotlinx.coroutines.launch

inline fun <reified T : EventBus.Event> LifecycleOwner.onEvent(
    repeatState: Lifecycle.State = Lifecycle.State.CREATED,
    crossinline action: (T) -> Unit,
) {
    lifecycleScope.launch {
        repeatOnLifecycle(repeatState) {
            EventBus.collect<T>(this) { action.invoke(it)}
        }
    }
}