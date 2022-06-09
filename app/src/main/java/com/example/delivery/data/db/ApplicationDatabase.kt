package com.example.delivery.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.delivery.data.db.dao.LocationDao
import com.example.delivery.data.entity.location.LocationLatLngEntity

@Database(
    entities = [LocationLatLngEntity::class],
    version = 1,
    exportSchema = false
)

abstract class ApplicationDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "ApplicationDataBase.db"
    }

    abstract fun LocationDao(): LocationDao

}