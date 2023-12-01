package com.covalent.locationfinderapp.data.model


import com.google.gson.annotations.SerializedName

data class UserLocation(
    @SerializedName("created")
    val created: String, // 0001-01-01T00:00:00
    @SerializedName("current")
    val current: Current,
    @SerializedName("driverId")
    val driverId: String, // driver-001
    @SerializedName("end")
    val end: End,
    @SerializedName("id")
    val id: String, // journey-001
    @SerializedName("journeyId")
    val journeyId: String, // journey-001
    @SerializedName("lastModified")
    val lastModified: String, // 0001-01-01T00:00:00
    @SerializedName("start")
    val start: Start,
    @SerializedName("status")
    val status: Boolean // true
)