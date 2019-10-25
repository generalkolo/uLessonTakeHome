package com.gokada.map.managers

import android.Manifest
import android.location.Location
import com.gokada.map.base.BaseMapActivity
import com.gokada.map.utils.locationRequest
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.RxLocation
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class GokadaSuperAppLocationManager<ACTIVITY : BaseMapActivity<*, *>>(
    val activity: ACTIVITY
) {

    private val rxPermission = RxPermissions(activity)
    private val rxLocation = RxLocation(activity.applicationContext)

    var emitLocation: Boolean = true
    var emitRideLocation: Boolean = false

    private val compositeDisposable = CompositeDisposable()

    val locationPublisher = PublishSubject.create<Location>()
    val rideLocationPublisher = PublishSubject.create<Location>()

    init {
        compositeDisposable.add(
            rxPermission.request(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ).subscribe {
                if (it) {
                    startLocationUpdates()
                    getRideCurrentLocation()
                }
            }
        )
    }

    private fun startLocationUpdates() {
        val locationRequest = locationRequest {
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
            interval = 5000
        }
        compositeDisposable.add(
            rxLocation.settings().checkAndHandleResolution(locationRequest).doOnSuccess {
                if (it) {
                    compositeDisposable.add(
                        rxLocation.location().updates(locationRequest).doOnNext { location ->
                            if (emitLocation) locationPublisher.onNext(location)
                        }.subscribe()
                    )
                }
            }.subscribe()
        )
    }

    private fun getRideCurrentLocation() {
        val currentLocationRequest = locationRequest {
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
            interval = 15000
        }
        compositeDisposable.add(
            rxLocation.location().updates(currentLocationRequest).doOnNext {
                if (emitRideLocation) rideLocationPublisher.onNext(it)
            }.subscribe()
        )
    }

    fun decodeLocation(location: Location): String {
        val address = rxLocation.geocoding().fromLocation(location).blockingGet()
        return address?.getAddressLine(0) ?: "Fake Address"
    }

    fun cleanup() {
        compositeDisposable.dispose()
        locationPublisher.onComplete()
        rideLocationPublisher.onComplete()
    }
}