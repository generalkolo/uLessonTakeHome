package com.gokada.map.models

import android.location.Location

data class GokadaSuperAppLocationModel <L> (
    var isFirstLocation: Boolean = false,
    var location: Location? = null,
    var data: L? = null
)

fun <L> superAppLocation (block: GokadaSuperAppLocationModel<L>.() -> Unit) = GokadaSuperAppLocationModel<L>().apply(block)