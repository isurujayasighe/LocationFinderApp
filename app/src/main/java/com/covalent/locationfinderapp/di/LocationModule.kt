package com.covalent.locationfinderapp.di

import android.content.Context
import android.util.Log
import com.covalent.locationfinderapp.data.repository.LocationServiceClientImpl
import com.covalent.locationfinderapp.data.repository.What3WordClientImpl
import com.covalent.locationfinderapp.domain.repository.LocationServiceClient
import com.covalent.locationfinderapp.domain.repository.What3WordClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.what3words.androidwrapper.What3WordsV3
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    const val API_KEY = ""

    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    @Singleton
    @Provides
    fun provideLocationServiceClient(
        @ApplicationContext context: Context,
        client: FusedLocationProviderClient,
    ) = LocationServiceClientImpl(context, client)

    @Singleton
    @Provides
    fun provideWhat3Words(@ApplicationContext context: Context): What3WordsV3 = What3WordsV3("5R1D26BC",context)

    @Singleton
    @Provides
    fun provideWhat3WordClient(
        @ApplicationContext context: Context,
        what3WordsV3: What3WordsV3
    ) = What3WordClientImpl(context, what3WordsV3)

}

@InstallIn(SingletonComponent::class)
@Module
interface LocationBind {

    @Binds
    fun locationBind(locationClientServiceImpl: LocationServiceClientImpl): LocationServiceClient

    @Binds
    fun what3WordBind(what3WordClientImpl: What3WordClientImpl): What3WordClient
}