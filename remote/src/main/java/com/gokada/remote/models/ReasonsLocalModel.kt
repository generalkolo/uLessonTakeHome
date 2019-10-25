package com.gokada.remote.models

import com.google.gson.annotations.SerializedName

data class ReasonsLocalModel(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("__v")
	val V: Int? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("tag")
	val tag: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)