package com.covalent.locationfinderapp.domain.repository

import com.covalent.locationfinderapp.data.model.UserLocation

interface AzureClient {
    suspend fun sendData(jsonData: String)

    suspend fun sendMessageToSignalR(message:String)

    fun sendDataToServiceBus(userLocation: UserLocation)
}