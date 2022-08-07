package com.example.delivery.ui.home

import android.app.Application
import android.util.Log
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
        //val currentLocation = userLocation ?: locationLatLngEntity
        val currentLocation = LocationLatLngEntity(37.504449,127.048860,-1)

        Log.d("동현","userLocation : ${userLocation}")
        Log.d("동현","12 : ${currentLocation}")


        getReverseGeoInformationUseCase.invoke(currentLocation)
            .onStart {
                _homeStateLiveData.value = HomeState.Loading
            }
            .catch {
                _homeStateLiveData.value = HomeState.Error(
                    messageId = R.string.can_not_load_address_info
                )
            }
            .onResult { addressInfo ->
                Log.d("동현", "addressInfo : $addressInfo")
                _homeStateLiveData.value = HomeState.Success(
                    MapSearchInfoEntity(
                        fullAddress = addressInfo.fullAddress ?: "주소 정보 없음",
                        name = addressInfo.buildingName ?: "빌딩정보 없음",
                        locationLatLng = currentLocation
                    ),
                    isLocationSame = currentLocation == locationLatLngEntity
                )
            }
    }
}
