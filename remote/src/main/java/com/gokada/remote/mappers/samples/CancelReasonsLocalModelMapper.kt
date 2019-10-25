package com.gokada.remote.mappers.samples

import com.gokada.remote.mappers.RemoteMapper
import com.gokada.remote.models.CancelReasonsLocalModel
import com.gokada.remote.models.ReasonsLocalModel
import com.gokada.repository.models.sampleModels.CancelReasonsEntity
import com.gokada.repository.models.sampleModels.ReasonsEntity
import javax.inject.Inject

/**
 * Created by edetebenezer on 2019-09-09
 **/
class CancelReasonsLocalModelMapper @Inject constructor(
    private val reasonsLocalModelMapper: ReasonsLocalModelMapper
) : RemoteMapper<CancelReasonsLocalModel, CancelReasonsEntity> {
    override fun mapToRepository(remote: CancelReasonsLocalModel): CancelReasonsEntity {
        val reasons = reasonsLocalModelMapper.mapToRepositoryList(remote.data!!)
        return CancelReasonsEntity(
            data = reasons
        )
    }
}

class ReasonsLocalModelMapper @Inject constructor(
) : RemoteMapper<ReasonsLocalModel, ReasonsEntity> {
    override fun mapToRepository(remote: ReasonsLocalModel): ReasonsEntity =
        ReasonsEntity(
            createdAt = remote.createdAt!!,
            id = remote.id!!,
            V = remote.V!!,
            tag = remote.tag!!,
            type = remote.type!!,
            body = remote.body!!,
            updatedAt = remote.updatedAt!!
        )
}