package com.gokada.remote.impl.samples

import com.gokada.remote.mappers.samples.FareCalculatorRemoteModelMapper
import com.gokada.remote.services.ManualRideService
import com.gokada.remote.utils.GokadaSuperAppRemoteConstants
import com.gokada.remote.utils.performActionOnError
import com.gokada.repository.models.BaseRepositoryModel
import com.gokada.repository.models.sampleModels.FareCalculatorEntity
import com.gokada.repository.remote.sample.ManualRideRemote
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by edetebenezer on 2019-08-09
 **/

class ManualRideRemoteImpl @Inject constructor(
    @Named(value = GokadaSuperAppRemoteConstants.RIDE_HAILING_RETROFIT) retrofit: Retrofit,
    private val fareCalculatorRemoteModelMapper: FareCalculatorRemoteModelMapper
) : ManualRideRemote {

    private val manualRideService = retrofit.create(ManualRideService::class.java)

    override fun calculateFareAmount(data: HashMap<String, Any>): Single<BaseRepositoryModel<FareCalculatorEntity>> =
        manualRideService.calculateFare(data).performActionOnError().map {
            BaseRepositoryModel(
                successful = it.success,
                message = it.message,
                data = it.data?.let { fareCalculatorLocalModel ->
                    fareCalculatorRemoteModelMapper.mapToRepository(fareCalculatorLocalModel)
                }
            )
        }
}