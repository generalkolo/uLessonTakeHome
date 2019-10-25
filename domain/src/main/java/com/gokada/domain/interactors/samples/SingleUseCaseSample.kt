package com.gokada.domain.interactors.samples

import com.gokada.domain.interactors.SingleUseCase
import com.gokada.domain.models.BaseDomainModel
import com.gokada.domain.repository.IPaymentRepository
import com.gokada.domain.utils.RxExecutionThread
import io.reactivex.Single
import javax.inject.Inject

class SingleUseCaseSample @Inject constructor(
    executionThread: RxExecutionThread,
    private val paymentRepository: IPaymentRepository
): SingleUseCase<SingleUseCaseSample.Parameters, String>(executionThread) {

    override fun buildSingleUseCase(param: Parameters?): Single<BaseDomainModel<String>> =
        param?.let {
            paymentRepository.deleteCard(
                hashMapOf(
                    "phoneNumber" to param.phoneNumber,
                    "cardToken" to param.cardToken
                )
            )
        } ?: throw IllegalArgumentException("Phone number and card details cannot be null!")

    data class Parameters (
        val phoneNumber: String, val cardToken: String
    ) {
        companion object {
            @JvmStatic
            fun make(phoneNumber: String, cardToken: String): Parameters =
                Parameters(
                    phoneNumber = phoneNumber,
                    cardToken = cardToken
                )
        }
    }

}