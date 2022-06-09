package com.example.delivery.data.repository

import com.example.delivery.data.entity.location.LocationLatLngEntity
import com.example.delivery.data.network.MapApiService
import com.example.delivery.data.response.adress.AddressInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MapRepositoryImpl(
    private val mapApiService: MapApiService
) : MapRepository{

    override fun getReverseGeoInformation(locationLatLngEntity: LocationLatLngEntity): Flow<AddressInfo> = flow {
        emit(mapApiService.getReverseGeoCode(
            lat = locationLatLngEntity.latitude,
            lon = locationLatLngEntity.longitude
        ))
    }.map { it.addressInfo }
}