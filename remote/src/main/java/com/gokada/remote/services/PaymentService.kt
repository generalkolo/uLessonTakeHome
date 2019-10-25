package com.gokada.remote.services

import com.gokada.remote.base.BaseDataWrapperRemote
import com.gokada.remote.base.BaseNetworkModel
import com.gokada.remote.models.StringResponse
import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.QueryMap

/**
 * Created by Isaac Iniongun on 2019-09-18.
 * For gokada-app-android project.
 */

interface PaymentService {

    @DELETE("card/deactivateCustomerCard")
    fun deleteCard(@QueryMap queryParameters: HashMap<String, String>): Single<BaseNetworkModel<BaseDataWrapperRemote<StringResponse>>>
}