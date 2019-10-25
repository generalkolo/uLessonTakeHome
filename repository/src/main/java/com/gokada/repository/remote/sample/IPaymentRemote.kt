package com.gokada.repository.remote.sample

import com.gokada.repository.models.BaseRepositoryModel
import io.reactivex.Single

interface IPaymentRemote {
    fun deleteCard(data: HashMap<String, String>): Single<BaseRepositoryModel<String>>
}