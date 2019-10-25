package com.gokada.repository.impl.samples

import com.gokada.domain.models.BaseDomainModel
import com.gokada.domain.repository.RidersRepository
import com.gokada.repository.remote.sample.IRiderRemote
import io.reactivex.Observable
import javax.inject.Inject

class RiderRepositoryImpl @Inject constructor(
    private val riderRemote: IRiderRemote
) : RidersRepository {

    override fun cancelRide(rideId: String): Observable<BaseDomainModel<String>> =
        riderRemote.cancelRide(rideId).toObservable().map {
            BaseDomainModel(
                successful = it.successful,
                message = it.message,
                data = it.data
            )
        }
}
