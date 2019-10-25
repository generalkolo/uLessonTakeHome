package com.gokada.map.managers

import android.content.Context
import com.gokada.core.listeners.SingleArgClickListener
import com.gokada.core.utils.BaseAppLog
import com.gokada.map.BuildConfig
import com.gokada.map.models.GokadaSuperAppPlaceModel
import com.gokada.map.models.superAppPlaceModel
import com.gokada.map.utils.autoCompleteRequest
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import io.reactivex.subjects.PublishSubject

class GoogleSuperAppPlacesManager constructor(
    context: Context
) {

    init {
        if (!Places.isInitialized()) Places.initialize(context, BuildConfig.GOOGLE_MAPS_API)
    }

    val placesPublisher = PublishSubject.create<List<GokadaSuperAppPlaceModel>>()
    private val client = Places.createClient(context)
    //Rectangular bounds for Nigeria -> SouthWest, NorthEast
    //(2.69170169436, 4.24059418377, 14.5771777686, 13.8659239771)
    private val bounds = RectangularBounds.newInstance(LatLngBounds(
        LatLng(2.69170169436, 4.24059418377),
        LatLng(14.5771777686, 13.8659239771)
    ))

    fun requestPredictions(query: String) {
        val token = AutocompleteSessionToken.newInstance()
        val request = autoCompleteRequest {
            setCountry("NG")
            setSessionToken(token)

            setQuery(query)
        }
        client.findAutocompletePredictions(request).addOnSuccessListener {
            placesPublisher.onNext(
                it.autocompletePredictions.map {
                    superAppPlaceModel {
                        address = it.getFullText(null).toString()
                        placeId = it.placeId
                    }
                }
            )
        }
    }

    fun fetchPlace(placeId: String, onPlaceFetched: SingleArgClickListener<Place>) {
        val placeFields = arrayListOf(Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)
        val placeRequest = FetchPlaceRequest.builder(placeId, placeFields).build()
        client.fetchPlace(placeRequest).addOnSuccessListener {
            BaseAppLog.e("Place fetched\t\t\t\t${it.place}")
            onPlaceFetched.invoke(it.place)
        }
    }
}