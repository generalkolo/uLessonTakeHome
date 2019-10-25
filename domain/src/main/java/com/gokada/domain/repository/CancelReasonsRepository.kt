package com.gokada.domain.repository

import com.gokada.domain.models.BaseDomainModel
import com.gokada.domain.models.sampleModels.CancelReasons
import io.reactivex.Observable

/**
 * Created by edetebenezer on 2019-09-09
 **/
interface CancelReasonsRepository {
    fun getCancelReasons(): Observable<BaseDomainModel<CancelReasons>>
}