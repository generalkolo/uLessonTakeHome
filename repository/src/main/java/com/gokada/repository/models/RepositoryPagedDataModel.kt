package com.gokada.repository.models

/**
 * Created by edetebenezer on 2019-08-24
 **/
data class RepositoryPagedDataModel<D>(
    val hasNextPage: Boolean,
    val currentPage: Int? = null,
    val data: D? = null
)