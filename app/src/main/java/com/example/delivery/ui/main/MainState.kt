package com.example.delivery.ui.main

import androidx.annotation.IdRes
import com.example.delivery.R

sealed class MainTabMenu(@IdRes val menuId: Int) {
    object Home : MainTabMenu(R.id.menu_home)
    object My : MainTabMenu(R.id.menu_my)
}