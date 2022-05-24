package com.example.delivery.ui.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class CustomRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    abstract fun <T> replaceData(list: List<T>?)
}