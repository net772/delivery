package com.example.delivery.domain.usecase

import com.example.delivery.data.entity.location.LocationLatLngEntity
import com.example.delivery.data.repository.MapRepository
import com.example.delivery.domain.UseCase

class GetReverseGeoInformationUseCase(
    private val mapRepository: MapRepository
) : UseCase {
    operator fun invoke(locationLatLngEntity: LocationLatLngEntity) = mapRepository.getReverseGeoInformation(locationLatLngEntity)
}