package com.gokada.domain.models

data class BaseDomainModel<D> (
    val successful: Boolean,
    val data: D? = null,
    val message: String? = null
)