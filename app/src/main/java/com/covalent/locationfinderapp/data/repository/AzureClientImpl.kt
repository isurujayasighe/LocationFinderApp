package com.covalent.locationfinderapp.data.repository

import android.util.Log
import com.azure.messaging.servicebus.ServiceBusMessage
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient
import com.azure.messaging.servicebus.ServiceBusSenderClient
import com.covalent.locationfinderapp.data.model.UserLocation
import com.covalent.locationfinderapp.domain.repository.AzureClient
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubException
import javax.inject.Inject

class AzureClientImpl @Inject constructor(
//    private val azureDocumentClient: AsyncDocumentClient
    private val hubConnection: HubConnection,
    private val serviceBusSenderAsyncClient: ServiceBusSenderClient
): AzureClient {

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
        try {
            Log.d("CO",userLocation.created)
            val result = serviceBusSenderAsyncClient.entityPath
            Log.d("CO",result)
            serviceBusSenderAsyncClient.sendMessage(ServiceBusMessage(userLocation.toString()))
        }catch (e:Exception){
            Log.d("CO_ERROR",e.message!!)
        }
    }
}