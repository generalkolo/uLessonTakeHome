package com.gokada.repository.remote.sample

import com.gokada.repository.models.BaseRepositoryModel
import com.gokada.repository.models.sampleModels.CancelReasonsEntity
import io.reactivex.Single

/**
 * Created by edetebenezer on 2019-09-09
 **/
interface CancelReasonsRemote {
    fun getCancelReasons(): Single<BaseRepositoryModel<CancelReasonsEntity>>
}