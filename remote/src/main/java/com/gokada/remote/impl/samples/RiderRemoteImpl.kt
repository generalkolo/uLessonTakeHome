package com.gokada.remote.impl.samples

import com.gokada.remote.services.RiderService
import com.gokada.remote.utils.GokadaSuperAppRemoteConstants
import com.gokada.remote.utils.performActionOnError
import com.gokada.repository.models.BaseRepositoryModel
import com.gokada.repository.remote.sample.IRiderRemote
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class RiderRemoteImpl @Inject constructor(
    @Named(value = GokadaSuperAppRemoteConstants.RIDE_HAILING_RETROFIT) retrofit: Retrofit
) : IRiderRemote {

    private val riderService = retrofit.create(RiderService::class.java)

    override fun cancelRide(rideId: String): Single<BaseRepositoryModel<String>> =
        riderService.cancelRide(rideId).performActionOnError().map {
            BaseRepositoryModel(
                successful = it.success,
                message = it.message,
                data = it.data?.response
            )
        }
}