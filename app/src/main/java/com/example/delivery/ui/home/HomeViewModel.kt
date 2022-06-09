package com.example.delivery.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.delivery.R
import com.example.delivery.data.entity.location.LocationLatLngEntity
import com.example.delivery.data.entity.location.MapSearchInfoEntity
import com.example.delivery.domain.usecase.GetReverseGeoInformationUseCase
import com.example.delivery.domain.usecase.GetUserLocationUseCase
import com.example.delivery.ui.base.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    app: Application,
    private val getUserLocationUseCase: GetUserLocationUseCase,
    private val getReverseGeoInformationUseCase: GetReverseGeoInformationUseCase
): BaseViewModel(app) {

    private val _homeStateLiveData = MutableStateFlow<HomeState>(HomeState.Uninitialized)
    val homeStateLiveData = _homeStateLiveData.asStateFlow()

    fun loadReverseGeoInformation(locationLatLngEntity: LocationLatLngEntity) = viewModelScope.launch {
        val userLocation = getUserLocationUseCase.invoke().firstOrNull()
        val currentLocation = userLocation ?: locationLatLngEntity

        val addressInfo = getReverseGeoInformationUseCase.invoke(currentLocation)
            .onStart {
                _homeStateLiveData.value = HomeState.Loading
            }
            .catch {
                _homeStateLiveData.value = HomeState.Error(
                    messageId = R.string.can_not_load_address_info
                )
            }
            .onResult { info ->
            _homeStateLiveData.value = HomeState.Success(
                MapSearchInfoEntity(
                    fullAddress = info.fullAddress ?: "주소 정보 없음",
                    name = info.buildingName ?: "빌딩정보 없음",
                    locationLatLng = currentLocation
                ),
                isLocationSame = currentLocation == locationLatLngEntity
            )
        }
    }
}