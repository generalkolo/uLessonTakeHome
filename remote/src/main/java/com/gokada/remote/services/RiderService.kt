package com.gokada.remote.services

import com.gokada.remote.base.BaseNetworkModel
import com.gokada.remote.models.TwoZeroFourRemoteModel
import io.reactivex.Single
import retrofit2.http.PATCH
import retrofit2.http.Path

interface RiderService {
    @PATCH("user/rides/{rideId}/cancel")
    fun cancelRide(@Path("rideId") rideId: String): Single<BaseNetworkModel<TwoZeroFourRemoteModel>>
}