package com.gokada.remote.models

import com.google.gson.annotations.SerializedName

data class CancelReasonsLocalModel(
	@field:SerializedName("data")
	val data: List<ReasonsLocalModel>? = null
)