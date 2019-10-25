package com.gokada.repository.impl.samples

import com.gokada.domain.models.BaseDomainModel
import com.gokada.domain.models.sampleModels.CancelReasons
import com.gokada.domain.repository.CancelReasonsRepository
import com.gokada.repository.mappers.samples.CancelReasonsEntityMapper
import com.gokada.repository.remote.sample.CancelReasonsRemote
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by edetebenezer on 2019-09-09
 **/
class CancelReasonsRepositoryImpl @Inject constructor(
    private val cancelReasonsRemote: CancelReasonsRemote,
    private val cancelReasonsEntityMapper: CancelReasonsEntityMapper
) : CancelReasonsRepository {

    override fun getCancelReasons(): Observable<BaseDomainModel<CancelReasons>> =
        cancelReasonsRemote.getCancelReasons().toObservable().map {
            BaseDomainModel(
                successful = it.successful,
                message = it.message,
                data = it.data.let { cancelReasonsEntity ->
                    cancelReasonsEntityMapper.mapToDomain(cancelReasonsEntity!!)
                }
            )
        }
}