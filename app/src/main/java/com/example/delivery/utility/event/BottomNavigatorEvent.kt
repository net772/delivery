package com.example.delivery.utility.event

import androidx.annotation.IdRes

sealed class BottomNavigatorEvent : EventBus.Event {
    data class Home(@IdRes val menuIds: Int) : BottomNavigatorEvent()
    data class My(@IdRes val menuIds: Int) : BottomNavigatorEvent()
}