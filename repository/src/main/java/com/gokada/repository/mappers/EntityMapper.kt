package com.gokada.repository.mappers

/**
 * Created by ayokunlepaul on 2019-07-17
 */
interface EntityMapper<ENTITY, DOMAIN> {

    fun mapToDomain(entity: ENTITY): DOMAIN

    fun mapToDomainList(entities: List<ENTITY>): List<DOMAIN> =
        entities.map { entity -> mapToDomain(entity) }

    fun safeString(value: String?) = value ?: "N/A"

    fun safeInt(value: Int?) = value ?: 0

    fun safeList(value: List<Any>?) = value ?: emptyList()
}