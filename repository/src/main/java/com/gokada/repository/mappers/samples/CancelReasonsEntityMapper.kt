package com.gokada.repository.mappers.samples

import com.gokada.domain.models.sampleModels.CancelReasons
import com.gokada.domain.models.sampleModels.Reasons
import com.gokada.repository.mappers.EntityMapper
import com.gokada.repository.models.sampleModels.CancelReasonsEntity
import com.gokada.repository.models.sampleModels.ReasonsEntity
import javax.inject.Inject

/**
 * Created by edetebenezer on 2019-09-09
 **/
class CancelReasonsEntityMapper @Inject constructor(
    private val reasonsEntityMapper: ReasonsEntityMapper
) : EntityMapper<CancelReasonsEntity, CancelReasons> {
    override fun mapToDomain(entity: CancelReasonsEntity): CancelReasons {
        val reasons = reasonsEntityMapper.mapToDomainList(entity.data)

        return CancelReasons(
            data = reasons
        )
    }
}

class ReasonsEntityMapper @Inject constructor(
) : EntityMapper<ReasonsEntity, Reasons> {
    override fun mapToDomain(entity: ReasonsEntity): Reasons =
        Reasons(
            createdAt = entity.createdAt,
            V = entity.V,
            id = entity.id,
            tag = entity.tag,
            type = entity.type,
            body = entity.body,
            updatedAt = entity.updatedAt
        )
}