package com.gokada.map.utils

import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.PolylineOptions

fun markerOptions (block: MarkerOptions.() -> Unit) = MarkerOptions().apply(block)

fun locationRequest (block: LocationRequest.() -> Unit): LocationRequest = LocationRequest.create().apply(block)

fun polyLineOptions (block: PolylineOptions.() -> Unit) = PolylineOptions().apply(block)

fun GoogleMap.addMarker(address: String) {

}