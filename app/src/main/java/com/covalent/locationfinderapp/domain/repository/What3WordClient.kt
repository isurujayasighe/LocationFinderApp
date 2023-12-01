package com.covalent.locationfinderapp.domain.repository

import kotlinx.coroutines.flow.Flow


interface What3WordClient {

    suspend fun getWhat3WordString(lat:Double,lng:Double):Flow<String>
}