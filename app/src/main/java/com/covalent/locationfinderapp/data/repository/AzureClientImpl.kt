package com.covalent.locationfinderapp.data.repository

import android.util.Log
import com.azure.messaging.servicebus.ServiceBusMessage
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient
import com.azure.messaging.servicebus.ServiceBusSenderClient
import com.covalent.locationfinderapp.data.model.UserLocation
import com.covalent.locationfinderapp.domain.repository.AzureClient
import com.fasterxml.jackson.databind.ObjectMapper
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubException
import javax.inject.Inject

class AzureClientImpl @Inject constructor(
//    private val azureDocumentClient: AsyncDocumentClient
    private val hubConnection: HubConnection,
    private val serviceBusSenderAsyncClient: ServiceBusSenderClient
): AzureClient {

    private var number: Int = 500

    override suspend fun sendData(jsonData: String) {}

    override suspend fun sendMessageToSignalR(message: String) {
        try {
            val connectionName = hubConnection.connectionState.name
            Log.d("CO_",connectionName)
            hubConnection.send("serverless")
        }catch (e:HubException){
            Log.d("CO_ERROR",e.message!!)
        }catch (e:Exception){
            Log.d("CO_ERROR",e.message!!)
        }
    }

    override fun sendDataToServiceBus(userLocation: UserLocation) {
        number++
        try {
            userLocation.apply {
                id  = "00$number"
            }
            val jsonObject = ObjectMapper()
            val message = jsonObject.writeValueAsString(userLocation)
            serviceBusSenderAsyncClient.sendMessage(ServiceBusMessage(message))
            Log.d("CO",userLocation.id)
        }catch (e:Exception){
            Log.d("CO_ERROR",e.message!!)
        }
    }
}