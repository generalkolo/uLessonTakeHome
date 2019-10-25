package com.gokada.domain.models

/**
 * Created by edetebenezer on 2019-08-24
 **/
data class DomainPagedDataModel<D>(
    val hasNextPage: Boolean,
    val currentPage: Int? = null,
    val data: D? = null
)