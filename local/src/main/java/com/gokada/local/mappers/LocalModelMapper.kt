package com.gokada.local.mappers

/**
 * Created by ayokunlepaul on 2019-07-17
 */
interface LocalModelMapper <LOCAL, REPOSITORY> {

    fun mapToLocal(repository: REPOSITORY): LOCAL

    fun mapToRepository(local: LOCAL): REPOSITORY

    fun mapToLocalList(repositories: List<REPOSITORY>) = repositories.map { mapToLocal(it) }

    fun mapToRepositories(localList: List<LOCAL>) = localList.map { mapToRepository(it) }

    fun safeString(value: String?) = value ?: "N/A"

    fun safeInt(value: Int?) = value ?: 0
}