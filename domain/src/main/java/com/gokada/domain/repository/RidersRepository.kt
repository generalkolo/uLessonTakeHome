package com.gokada.domain.repository

import com.gokada.domain.models.BaseDomainModel
import io.reactivex.Observable

interface RidersRepository {
    fun cancelRide(rideId: String): Observable<BaseDomainModel<String>>
}