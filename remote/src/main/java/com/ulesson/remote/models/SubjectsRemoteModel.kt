package com.ulesson.remote.models

import com.google.gson.annotations.SerializedName

data class SubjectsRemoteModel(

	@field:SerializedName("data")
	val subjectsRemoteDataModel: SubjectsRemoteDataModel? = null
)

data class ChaptersRemoteItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("lessons")
	val lessons: List<LessonsRemoteItem>? = null
)

data class SubjectsRemoteItem(

	@field:SerializedName("chapters")
	val chapters: List<ChaptersRemoteItem>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class LessonsRemoteItem(

	@field:SerializedName("subject_id")
	val subjectId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("chapter_id")
	val chapterId: Int? = null,

	@field:SerializedName("media_url")
	val mediaUrl: String? = null
)

data class SubjectsRemoteDataModel(

	@field:SerializedName("subjects")
	val subjects: List<SubjectsRemoteItem>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
