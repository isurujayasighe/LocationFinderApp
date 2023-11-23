package com.covalent.locationfinderapp.ui.screens.location

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.covalent.locationfinderapp.domain.repository.AzureCosmosClient
import com.covalent.locationfinderapp.domain.repository.LocationServiceClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationServiceClient: LocationServiceClient,
    azureCosmosClient: AzureCosmosClient
) : ViewModel() {

    private val _uiState = MutableStateFlow(LocationState())
    val uiState: MutableStateFlow<LocationState> = _uiState

    init {
        Log.d("LO_", "LocationViewModel Injected")
        azureCosmosClient.sendData("hello Isuru!!")
    }

    fun getLocationUpdates() {
        viewModelScope.launch {
            locationServiceClient.getLocationUpdates(1L)
                .catch {
                    Log.d("CO_",it.message!!)
                }
                .collect {
                    _uiState.value = LocationState(
                        lat = it.latitude.toString(),
                        long = it.longitude.toString()
                    )
                }
        }
    }
}