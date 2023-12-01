package com.covalent.locationfinderapp.di

import android.app.Application
import android.content.Context
import com.azure.core.credential.TokenCredential
import com.azure.cosmos.CosmosAsyncClient
import com.azure.cosmos.CosmosClient
import com.azure.cosmos.CosmosClientBuilder
import com.azure.cosmos.implementation.ConnectionPolicy
import com.azure.cosmos.implementation.ConnectionPolicy.getDefaultPolicy
import com.azure.messaging.servicebus.ServiceBusClientBuilder
import com.azure.messaging.servicebus.ServiceBusReceiverClient
import com.azure.messaging.servicebus.ServiceBusSenderAsyncClient
import com.azure.messaging.servicebus.ServiceBusSenderClient
import com.covalent.locationfinderapp.data.repository.AzureClientImpl
import com.covalent.locationfinderapp.domain.repository.AzureClient
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AzureModule {

    private const val ENDPOINT_URI =
        "https://covalent-cosmosdb.documents.azure.com:443/"
    private const val PRIMARY_KEY =
        "ukeHaS50Q5vTl7Wo7TuKH5ZvlBXRZJjqN3FZ3dgdfje0CPotQ20fiUD7VTlxstxo98arJY07XRSUACDbAKPkDA=="
    private const val DATABASE_ID = "Drivers"
    private const val COLLECTION_ID = "UzQsAMrQbC4BAAAAAAAAAA=="

    @Singleton
    @Provides
    fun provideConnectionPolicy(): ConnectionPolicy {
        return getDefaultPolicy()
    }

    @Singleton
    @Provides
    fun provideCosmosAsyncClient(application: Application): CosmosAsyncClient =
        CosmosClientBuilder()
            .endpoint(ENDPOINT_URI)
            .key(PRIMARY_KEY)
            .buildAsyncClient()

    @Singleton
    @Provides
    fun provideCosmosClient(application: Application): CosmosClient = CosmosClientBuilder()
        .endpoint(ENDPOINT_URI)
        .key(PRIMARY_KEY)
        .buildClient()

    @Singleton
    @Provides
    fun provideSignalrRClient(): HubConnection =
        HubConnectionBuilder.create("https://covalent-hub.service.signalr.net/client/?hub=serverless")
            .build()


    @Singleton
    @Provides
    fun provideServiceBusSenderClient(@ApplicationContext context: Context): ServiceBusSenderClient =
        ServiceBusClientBuilder()
            .connectionString("Endpoint=sb://mi-servicebus.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=w719+81prXRcTOvYPFgw35U52l5jBLw1s+ASbNROBUI=")
            .sender()
            .queueName("location")
            .buildClient()

}

@InstallIn(SingletonComponent::class)
@Module
interface CosmosBind {

    @Binds
    fun cosmosBind(azureClientImpl: AzureClientImpl): AzureClient
}

