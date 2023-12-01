package com.covalent.locationfinderapp.ui.screens.location

data class LocationState(
    val lat: String = "",
    val long: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
)
