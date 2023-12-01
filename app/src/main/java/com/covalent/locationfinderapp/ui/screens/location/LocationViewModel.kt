package com.covalent.locationfinderapp.ui.screens.location

import android.content.Context
import android.util.Log
import android.util.TimeUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azure.cosmos.implementation.Document
import com.covalent.locationfinderapp.data.model.Current
import com.covalent.locationfinderapp.data.model.End
import com.covalent.locationfinderapp.data.model.Start
import com.covalent.locationfinderapp.data.model.UserLocation
import com.covalent.locationfinderapp.domain.repository.AzureClient
import com.covalent.locationfinderapp.domain.repository.LocationServiceClient
import com.covalent.locationfinderapp.domain.repository.What3WordClient
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationServiceClient: LocationServiceClient,
    private val azureClient: AzureClient,
    private val what3WordClient: What3WordClient,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LocationState())
    val uiState: MutableStateFlow<LocationState> = _uiState

    init {
        Log.d("LO_", "LocationViewModel Injected")
//        sendData()
    }

    private fun sendData(){
         val DATABASE_ID = "Drivers"
         val COLLECTION_ID = "UzQsAMrQbC4BAAAAAAAAAA=="

        try {
            val document = Document("Hello Isuru")

            val databaseLink = "dbs/$DATABASE_ID"
            val collectionLink = "$databaseLink/colls/$COLLECTION_ID"

//            azureDocumentClient.createDocument(collectionLink,document,null,false)

        }catch (e: Exception){
            Log.d("AZURE_",e.toString())
        }
    }

    fun getDataFromCosmos(){
//        viewModelScope.launch(Dispatchers.IO){
//            azureCosmoClient.sendData("")
//        }
        viewModelScope.launch(Dispatchers.IO){
           try {
               azureClient.sendMessageToSignalR("")
           }catch (e:HubException){
               Log.d("CO_ERROR",e.message!!)
           }
        }
    }

    fun sendDataToServiceBus(){
        try {
            azureClient.sendDataToServiceBus(UserLocation(
                created = Calendar.getInstance().timeInMillis.toString(),
                current = Current(69.12254454,70.2554412),
                driverId = "driver-001",
                end = End(69.12254454,70.2554412),
                id = "",
                journeyId = "",
                lastModified = Calendar.getInstance().timeInMillis.toString(),
                start = Start(69.12254454,70.2554412),
                status = false
            ))
        }catch (e:Exception){
            Log.d("CO_ERROR",e.message!!)
        }

    }

    fun getLocationUpdates() {
        viewModelScope.launch {
            locationServiceClient.getLocationUpdates(20000L)
                .catch {
                    Log.d("CO_",it.message!!)
                }
                .collect {
                    _uiState.value = LocationState(
                        lat = it.latitude.toString(),
                        long = it.longitude.toString(),
                        isLoading = false,
                        isSuccess = true
                    )

                    sendDataToServiceBus()
                }
        }
    }

    fun getCurrentLocation(){
        viewModelScope.launch {
            locationServiceClient.getCurrentLocation()
                .catch {
                    Log.d("CO_",it.message!!)
                }.collect{
                _uiState.value = LocationState(
                    lat = it.latitude.toString(),
                    long = it.longitude.toString(),
                    isLoading = false,
                    isSuccess = true
                )
            }
        }
    }

    fun getWhat3WordsString(lat:Double, long:Double){
       viewModelScope.launch (Dispatchers.IO) {
           what3WordClient.getWhat3WordString(lat, long)
               .catch {
                   Log.d("CO_Error","${it.message}")
               }.collect{
               Log.d("CO_",it)
           }
       }
    }
}