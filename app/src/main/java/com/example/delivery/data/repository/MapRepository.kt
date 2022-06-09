package com.example.delivery.data.repository

import com.example.delivery.data.entity.location.LocationLatLngEntity
import com.example.delivery.data.response.adress.AddressInfo
import kotlinx.coroutines.flow.Flow

interface MapRepository {

    fun getReverseGeoInformation(locationLatLngEntity: LocationLatLngEntity) : Flow<AddressInfo>

}