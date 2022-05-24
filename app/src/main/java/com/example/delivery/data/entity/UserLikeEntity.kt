package com.example.delivery.data.entity

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@androidx.room.Entity
data class UserLikeEntity(
    @PrimaryKey val id: Long,
    val avatar_url: String,
    val login: String,
    val html_url: String,
    val state: Boolean
): Parcelable