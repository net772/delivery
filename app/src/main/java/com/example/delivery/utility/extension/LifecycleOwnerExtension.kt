package com.example.delivery.utility.extension

import android.util.Log
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
            EventBus.collect<T>(this) {
                Log.d("동현","onEvent123")
                action.invoke(it)}
        }
    }
}