package com.gokada.remote.services

import com.gokada.remote.base.BaseNetworkModel
import com.gokada.remote.models.CancelReasonsLocalModel
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by edetebenezer on 2019-09-09
 **/
interface CancelReasonsService {
    @GET("user/cancelReasons")
    fun getCancelReasons(): Single<BaseNetworkModel<CancelReasonsLocalModel>>
}