package com.ulesson.takehome.models

import androidx.annotation.DrawableRes

data class ServiceModel (
    val name: String,
    @DrawableRes val iconResource: Int
)
