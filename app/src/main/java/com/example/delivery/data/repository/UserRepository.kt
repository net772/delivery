package com.example.delivery.data.repository

import com.example.delivery.data.entity.location.LocationLatLngEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserLocation(): Flow<LocationLatLngEntity>


}