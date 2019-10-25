package com.gokada.remote.impl.samples

import com.gokada.remote.services.PaymentService
import com.gokada.remote.utils.GokadaSuperAppRemoteConstants
import com.gokada.repository.models.BaseRepositoryModel
import com.gokada.repository.remote.sample.IPaymentRemote
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class PaymentRemoteImpl @Inject constructor(
    @Named(value = GokadaSuperAppRemoteConstants.PAYMENT_RETROFIT) paymentRetrofit: Retrofit
) : IPaymentRemote {

    private val paymentService = paymentRetrofit.create(PaymentService::class.java)

    override fun deleteCard(data: HashMap<String, String>) = paymentService.deleteCard(data).map {
        BaseRepositoryModel(
            successful = it.success,
            message = it.message,
            data = it.data?.response?.message
        )
    }
}