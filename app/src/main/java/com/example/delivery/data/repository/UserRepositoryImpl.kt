package com.example.delivery.data.repository

import com.example.delivery.data.db.dao.LocationDao
import com.example.delivery.data.entity.location.LocationLatLngEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val locationDao: LocationDao
) : UserRepository {
    override fun getUserLocation(): Flow<LocationLatLngEntity> = flow {
        emit(locationDao.get(-1))
    }.map { it }
}