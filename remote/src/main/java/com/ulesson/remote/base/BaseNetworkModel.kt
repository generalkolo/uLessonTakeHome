package com.ulesson.remote.base

import com.google.gson.annotations.SerializedName

data class BaseNetworkModel<T> (
    @SerializedName("data", alternate = ["response"]) val data: T? = null,
    val success: Boolean,
    val error: Boolean? = false,
    val message: String? = null,
    val code: Int? = 0
)
