package com.example.delivery.utility

import android.widget.Toast
import androidx.annotation.StringRes
import com.example.delivery.Application

object SingleToast {

    private var toast: Toast? = null

    fun showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
        toast?.cancel()

        toast = Toast.makeText(Application.applicationContext, message, duration)
    }
}