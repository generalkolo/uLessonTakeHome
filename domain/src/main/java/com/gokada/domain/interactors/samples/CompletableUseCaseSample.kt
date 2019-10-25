package com.gokada.domain.interactors.samples

import com.gokada.domain.interactors.CompletableUseCase
import com.gokada.domain.repository.RidersRepository
import com.gokada.domain.utils.RxExecutionThread
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by edetebenezer on 2019-09-09
 **/
class CompletableUseCaseSample @Inject constructor(
    executionThread: RxExecutionThread,
    private val iRiderRepository: RidersRepository
) : CompletableUseCase<CompletableUseCaseSample.Parameter>(executionThread) {
    override fun buildUseCaseCompletable(param: Parameter?): Completable = param?.let {
        iRiderRepository.cancelRide(
            param.rideId
        ).flatMapCompletable { domainModel ->
            if (domainModel.successful) Completable.complete()
            else Completable.error(Throwable(domainModel.message))
        }
    } ?: throw IllegalArgumentException("Parameter cannot be null")

    data class Parameter(
        val rideId: String
    ) {
        companion object {
            @JvmStatic
            fun make(
                rideId: String
            ): Parameter =
                Parameter(
                    rideId = rideId
                )
        }
    }
}