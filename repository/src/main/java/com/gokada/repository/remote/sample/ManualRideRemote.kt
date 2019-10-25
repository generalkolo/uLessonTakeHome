package com.gokada.repository.remote.sample

import com.gokada.repository.models.BaseRepositoryModel
import com.gokada.repository.models.sampleModels.FareCalculatorEntity
import io.reactivex.Single

/**
 * Created by edetebenezer on 2019-08-09
 **/
interface ManualRideRemote {
    fun calculateFareAmount(data: HashMap<String, Any>): Single<BaseRepositoryModel<FareCalculatorEntity>>
}