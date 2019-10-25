package com.gokada.map.utils

import android.location.Location
import android.location.LocationManager
import com.google.android.gms.maps.model.LatLng

val LatLng.location: Location
    get() = Location(LocationManager.GPS_PROVIDER).apply {
        this.latitude = this@location.latitude
        this.longitude = this@location.longitude
    }

val Location.latLng: LatLng
    get() = LatLng(latitude, longitude)