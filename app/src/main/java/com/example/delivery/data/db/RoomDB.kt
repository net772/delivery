package com.example.delivery.data.db

import android.content.Context
import androidx.room.Room

fun provideDB(context: Context): ApplicationDatabase =
Room.databaseBuilder(context, ApplicationDatabase::class.java, ApplicationDatabase.DB_NAME).build()

fun provideUserLikeDao(database: ApplicationDatabase) = database.UserLikeDao()