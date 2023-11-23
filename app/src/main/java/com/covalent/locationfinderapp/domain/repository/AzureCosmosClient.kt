package com.covalent.locationfinderapp.domain.repository

interface AzureCosmosClient {
    fun sendData(jsonData: String)
}