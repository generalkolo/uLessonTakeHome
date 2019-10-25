package com.gokada.remote.impl.samples

import com.gokada.remote.mappers.samples.CancelReasonsLocalModelMapper
import com.gokada.remote.services.CancelReasonsService
import com.gokada.remote.utils.GokadaSuperAppRemoteConstants
import com.gokada.remote.utils.performActionOnError
import com.gokada.repository.models.BaseRepositoryModel
import com.gokada.repository.models.sampleModels.CancelReasonsEntity
import com.gokada.repository.remote.sample.CancelReasonsRemote
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by edetebenezer on 2019-09-09
 **/
class CancelReasonRemoteImpl @Inject constructor(
    @Named(value = GokadaSuperAppRemoteConstants.RIDE_HAILING_RETROFIT) retrofit: Retrofit,
    private val cancelReasonsLocalModelMapper: CancelReasonsLocalModelMapper
) : CancelReasonsRemote {

    private val cancelReasonService = retrofit.create(CancelReasonsService::class.java)

    override fun getCancelReasons(): Single<BaseRepositoryModel<CancelReasonsEntity>> =
        cancelReasonService.getCancelReasons().performActionOnError().map {
            BaseRepositoryModel(
                successful = it.success,
                message = it.message,
                data = it.data.let { cancelReasons ->
                    cancelReasonsLocalModelMapper.mapToRepository(cancelReasons!!)
                }
            )
        }
}