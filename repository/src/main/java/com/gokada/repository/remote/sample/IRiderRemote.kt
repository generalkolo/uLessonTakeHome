package com.gokada.repository.remote.sample

import com.gokada.repository.models.BaseRepositoryModel
import io.reactivex.Single

interface IRiderRemote {
    fun cancelRide(rideId: String): Single<BaseRepositoryModel<String>>
}