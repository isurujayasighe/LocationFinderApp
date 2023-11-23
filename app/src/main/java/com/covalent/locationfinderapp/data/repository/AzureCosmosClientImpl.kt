package com.covalent.locationfinderapp.data.repository

import android.util.Log
import com.azure.cosmos.implementation.AsyncDocumentClient
import com.azure.cosmos.implementation.Document
import com.covalent.locationfinderapp.domain.repository.AzureCosmosClient
import javax.inject.Inject

class AzureCosmosClientImpl @Inject constructor(
    private val azureDocumentClient: AsyncDocumentClient
): AzureCosmosClient {
    private val DATABASE_ID = "your_database_id"
    private val COLLECTION_ID = "your_collection_id"

    override fun sendData(jsonData: String) {

        try {
            val document = Document(jsonData)

            val databaseLink = "dbs/$DATABASE_ID"
            val collectionLink = "$databaseLink/colls/$COLLECTION_ID"

            azureDocumentClient.createDocument(collectionLink,document,null,false)
        }catch (e: Exception){
            Log.d("AZURE_",e.toString())
        }

    }
}