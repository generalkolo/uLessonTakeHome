package com.gokada.repository.impl.samples

import com.gokada.domain.models.BaseDomainModel
import com.gokada.domain.repository.IPaymentRepository
import com.gokada.repository.remote.sample.IPaymentRemote
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val paymentRemote: IPaymentRemote
) : IPaymentRepository {

    override fun deleteCard(data: HashMap<String, String>) = paymentRemote.deleteCard(data).map {
        BaseDomainModel(
            successful = it.successful,
            message = it.message,
            data = it.data
        )
    }
}