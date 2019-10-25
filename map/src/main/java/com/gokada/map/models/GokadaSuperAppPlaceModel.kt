package com.gokada.map.models

data class GokadaSuperAppPlaceModel (
    var address: String? = null,
    var placeId: String? = null
)

fun superAppPlaceModel (block: GokadaSuperAppPlaceModel.() -> Unit) = GokadaSuperAppPlaceModel().apply(block)