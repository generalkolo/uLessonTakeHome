package com.gokada.remote.models

import com.google.gson.annotations.SerializedName

data class FareCalculatorLocalModel(

	@field:SerializedName("duration")
	val duration: Int? = null,

	@field:SerializedName("totalTo")
	val totalTo: Int? = null,

	@field:SerializedName("distance")
	val distance: Int? = null,

	@field:SerializedName("codedCoordinates")
	val codedCoordinates: String? = null,

	@field:SerializedName("perDuration")
	val perDuration: Int? = null,

	@field:SerializedName("totalFrom")
	val totalFrom: Int? = null,

	@field:SerializedName("perDistance")
	val perDistance: Int? = null,

	@field:SerializedName("base")
	val base: Int? = null
)