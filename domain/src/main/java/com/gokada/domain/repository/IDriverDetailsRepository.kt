package com.gokada.domain.repository

import com.gokada.domain.models.BaseDomainModel
import com.gokada.domain.models.sampleModels.FareCalculator
import io.reactivex.Observable

/**
 * Created by edetebenezer on 2019-08-09
 **/

interface IDriverDetailsRepository {
    fun calculateFare(data: HashMap<String, Any>): Observable<BaseDomainModel<FareCalculator>>
}