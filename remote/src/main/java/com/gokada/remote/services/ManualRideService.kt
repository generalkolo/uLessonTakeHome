package com.gokada.remote.services

import com.gokada.remote.base.BaseNetworkModel
import com.gokada.remote.models.FareCalculatorLocalModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by edetebenezer on 2019-08-09
 **/

interface ManualRideService {
    @POST("fare/calculation")
    fun calculateFare(@Body data: HashMap<String, Any>): Single<BaseNetworkModel<FareCalculatorLocalModel>>
}