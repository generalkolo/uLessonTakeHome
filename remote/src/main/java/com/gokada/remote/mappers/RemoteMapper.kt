package com.gokada.remote.mappers

/**
 * Created by ayokunlepaul on 2019-07-17
 */
interface RemoteMapper<REMOTE, ENTITY> {

    fun mapToRepository(remote: REMOTE): ENTITY

    fun mapToRepositoryList(values: List<REMOTE>): List<ENTITY> =
        values.map { value -> mapToRepository(value) }

    fun safeString(value: String?) = value ?: "N/A"
}