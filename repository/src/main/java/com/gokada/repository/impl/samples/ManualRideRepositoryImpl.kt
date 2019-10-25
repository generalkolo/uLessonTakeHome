package com.gokada.repository.impl.samples

import com.gokada.domain.models.BaseDomainModel
import com.gokada.domain.models.sampleModels.FareCalculator
import com.gokada.domain.repository.IDriverDetailsRepository
import com.gokada.repository.mappers.samples.FareCalculatorEntityMapper
import com.gokada.repository.remote.sample.ManualRideRemote
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by edetebenezer on 2019-08-11
 **/
class ManualRideRepositoryImpl @Inject constructor(
    private val manualRideDetailsRemote: ManualRideRemote,
    private val fareCalculatorEntityMapper: FareCalculatorEntityMapper
) : IDriverDetailsRepository {

    override fun calculateFare(data: HashMap<String, Any>): Observable<BaseDomainModel<FareCalculator>> =
        manualRideDetailsRemote.calculateFareAmount(data).toObservable().map {
            BaseDomainModel(
                successful = it.successful,
                message = it.message,
                data = it.data?.let { fareCalculator ->
                    fareCalculatorEntityMapper.mapToDomain(fareCalculator)
                }
            )
        }
}