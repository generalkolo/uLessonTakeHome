package com.gokada.remote.models

import com.google.gson.annotations.SerializedName

/**
 * Created by edetebenezer on 2019-10-09
 **/
data class TwoZeroFourRemoteModel (
    @SerializedName("success", alternate = ["message"]) val response: String
)