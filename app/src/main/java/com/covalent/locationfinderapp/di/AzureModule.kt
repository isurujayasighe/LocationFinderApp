package com.covalent.locationfinderapp.di

import com.azure.cosmos.implementation.AsyncDocumentClient
import com.azure.cosmos.implementation.ConnectionPolicy
import com.azure.cosmos.implementation.ConnectionPolicy.getDefaultPolicy
import com.covalent.locationfinderapp.data.repository.AzureCosmosClientImpl
import com.covalent.locationfinderapp.data.repository.LocationServiceClientImpl
import com.covalent.locationfinderapp.domain.repository.AzureCosmosClient
import com.covalent.locationfinderapp.domain.repository.LocationServiceClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AzureModule {

    private const val ENDPOINT_URI = "https://cosmosrgeastus31d5f34f-51c5-42ab-9e46db.documents.azure.com:443/"
    private const val PRIMARY_KEY = "userId"
    private const val DATABASE_ID = "your_database_id"
    private const val COLLECTION_ID = "your_collection_id"

    @Singleton
    @Provides
    fun provideConnectionPolicy():ConnectionPolicy{
        return getDefaultPolicy()
    }

    @Singleton
    @Provides
    fun provideAzureCosmosClient():AsyncDocumentClient{
        val connectionPolicy = getDefaultPolicy()
        return AsyncDocumentClient.Builder()
            .withServiceEndpoint(ENDPOINT_URI)
            .withMasterKeyOrResourceToken(PRIMARY_KEY)
            .withConnectionPolicy(connectionPolicy)
            .build()
    }
}

@InstallIn(SingletonComponent::class)
@Module
interface CosmosBind {

    @Binds
    fun cosmosBind(azureCosmosClientImpl: AzureCosmosClientImpl): AzureCosmosClient
}

