package com.covalent.locationfinderapp.data.model


import com.google.gson.annotations.SerializedName

data class Start(
    @SerializedName("latitude")
    val latitude: Double, // 7.291418
    @SerializedName("longitude")
    val longitude: Double // 80.636696
)