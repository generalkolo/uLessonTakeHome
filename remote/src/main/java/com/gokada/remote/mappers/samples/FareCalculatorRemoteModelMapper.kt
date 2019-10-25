package com.gokada.remote.mappers.samples

import com.gokada.remote.mappers.RemoteMapper
import com.gokada.remote.models.FareCalculatorLocalModel
import com.gokada.repository.models.sampleModels.FareCalculatorEntity
import javax.inject.Inject

/**
 * Created by edetebenezer on 2019-08-22
 **/
class FareCalculatorRemoteModelMapper @Inject constructor(
): RemoteMapper<FareCalculatorLocalModel, FareCalculatorEntity> {
    override fun mapToRepository(remote: FareCalculatorLocalModel): FareCalculatorEntity =
        FareCalculatorEntity(
            duration = remote.duration,
            totalFrom = remote.totalFrom,
            totalTo = remote.totalTo,
            distance = remote.distance,
            codedCoordinates = remote.codedCoordinates,
            perDuration = remote.perDuration,
            perDistance = remote.perDistance,
            base = remote.base
        )

}