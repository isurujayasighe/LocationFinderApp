package com.covalent.locationfinderapp.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import com.covalent.locationfinderapp.domain.repository.LocationServiceClient
import com.covalent.locationfinderapp.util.Util.hasLocationPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationServiceClientImpl @Inject constructor(
    private val context: Context,
    private val client: FusedLocationProviderClient
) : LocationServiceClient {
    @SuppressLint("ServiceCast", "MissingPermission")
    override fun getLocationUpdates(intervals: Long): Flow<Location> {
        return callbackFlow {
            if (!context.hasLocationPermission()) {
               throw LocationServiceClient.LocationException("Permission Not enabled!!")
            }

            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnabled =
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (!isGpsEnabled && !isNetworkEnabled) {
                Log.d("LOCATION", "Network Disabled")
            }

            val request =
                LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, intervals).apply {
                    setMinUpdateDistanceMeters(0.1F)
                    setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                    setWaitForAccurateLocation(true)
                }.build()

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let { location ->
                        launch { send(location) }
                    }
                }
            }

            client.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )

            awaitClose {
                client.removeLocationUpdates(locationCallback)
            }
        }
    }
}