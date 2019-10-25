package com.gokada.domain.interactors.samples

import com.gokada.domain.interactors.ObservableUseCaseNoParam
import com.gokada.domain.models.BaseDomainModel
import com.gokada.domain.models.sampleModels.CancelReasons
import com.gokada.domain.repository.CancelReasonsRepository
import com.gokada.domain.utils.RxExecutionThread
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by edetebenezer on 2019-09-09
 **/
class ObservableNoParamUseCaseSample @Inject constructor(
    executionThread: RxExecutionThread,
    private val cancelReasonsRepository: CancelReasonsRepository
) : ObservableUseCaseNoParam<BaseDomainModel<CancelReasons>>(executionThread) {

    override fun buildUseCaseObservable(): Observable<BaseDomainModel<CancelReasons>> =
        cancelReasonsRepository.getCancelReasons()
}