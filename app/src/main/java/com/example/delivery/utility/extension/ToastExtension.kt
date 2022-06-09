package com.example.delivery.utility.extension

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.delivery.utility.SingleToast

fun Fragment.showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT): Unit =
    SingleToast.showToast(message, duration)
