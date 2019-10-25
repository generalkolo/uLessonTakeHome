package com.gokada.repository.models

data class BaseRepositoryModel<D> (
    val successful: Boolean,
    val data: D? = null,
    val message: String? = null
)