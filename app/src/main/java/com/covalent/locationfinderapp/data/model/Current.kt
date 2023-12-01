package com.covalent.locationfinderapp.data.model


import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("latitude")
    val latitude: Double, // 7.08731
    @SerializedName("longitude")
    val longitude: Double // 80.014366
)