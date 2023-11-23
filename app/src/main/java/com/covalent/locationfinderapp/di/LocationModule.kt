package com.covalent.locationfinderapp.di

import android.content.Context
import com.covalent.locationfinderapp.data.repository.LocationServiceClientImpl
import com.covalent.locationfinderapp.domain.repository.LocationServiceClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    @Singleton
    @Provides
    fun provideLocationServiceClient(
        @ApplicationContext context: Context,
        client: FusedLocationProviderClient
    ) = LocationServiceClientImpl(context, client)
}

@InstallIn(SingletonComponent::class)
@Module
interface LocationBind {

    @Binds
    fun locationBind(locationClientServiceImpl: LocationServiceClientImpl): LocationServiceClient
}