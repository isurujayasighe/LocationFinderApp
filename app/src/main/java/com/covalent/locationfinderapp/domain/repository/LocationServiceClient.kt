package com.covalent.locationfinderapp.domain.repository

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationServiceClient {

    fun getLocationUpdates(intervals: Long): Flow<Location>

    class LocationException(message: String) : Exception()

    fun getCurrentLocation():Flow<Location>

    fun removeLocationUpdate():Flow<Boolean>

}