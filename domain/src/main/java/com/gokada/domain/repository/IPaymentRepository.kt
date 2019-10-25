package com.gokada.domain.repository

import com.gokada.domain.models.BaseDomainModel
import io.reactivex.Single

interface IPaymentRepository {
    fun deleteCard(data: HashMap<String, String>): Single<BaseDomainModel<String>>
}