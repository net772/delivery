package com.example.delivery.utility.event

import androidx.annotation.IdRes

sealed class BottomNavigatorEvent(@IdRes val menuId: Int) : EventBus.Event {
    data class Home(@IdRes val menuIds: Int) : BottomNavigatorEvent(menuIds)
    data class My(@IdRes val menuIds: Int) : BottomNavigatorEvent(menuIds)
}