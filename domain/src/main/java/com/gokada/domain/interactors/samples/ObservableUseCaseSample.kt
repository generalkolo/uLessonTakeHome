package com.gokada.domain.interactors.samples

import com.gokada.domain.interactors.ObservableUseCase
import com.gokada.domain.models.sampleModels.FareCalculator
import com.gokada.domain.models.sampleModels.RequestedDropOff
import com.gokada.domain.models.sampleModels.RequestedPickup
import com.gokada.domain.repository.IDriverDetailsRepository
import com.gokada.domain.utils.RxExecutionThread
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by edetebenezer on 2019-08-22
 **/
class ObservableUseCaseSample @Inject constructor(
    executionThread: RxExecutionThread,
    private val IDriverDetailsRepository: IDriverDetailsRepository
) : ObservableUseCase<ObservableUseCaseSample.Parameter, FareCalculator>(executionThread) {
    override fun buildUseCaseObservable(param: Parameter?): Observable<FareCalculator> =
        param?.let {
            IDriverDetailsRepository.calculateFare(
                hashMapOf(
                    "iso3166" to param.iso3166,
                    "requestedDropOff" to param.requestedDropOff,
                    "requestedPickup" to param.requestedPickup
                )
            ).map {
                it.data!!
            }
        } ?: throw IllegalArgumentException("Parameters cannot be null")

    data class Parameter(
        val iso3166: String,
        val requestedDropOff: RequestedDropOff,
        val requestedPickup: RequestedPickup
    ) {
        companion object {
            @JvmStatic
            fun make(
                iso3166: String,
                requestedDropOff: RequestedDropOff,
                requestedPickup: RequestedPickup
            ): Parameter =
                Parameter(
                    iso3166 = iso3166,
                    requestedDropOff = requestedDropOff,
                    requestedPickup = requestedPickup
                )
        }
    }
}