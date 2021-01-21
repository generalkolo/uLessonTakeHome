package com.ulesson.domain.models

data class ChaptersItem(
    val name: String,
    val id: Int,
    val lessons: List<LessonsItem>
)

data class SubjectsItem(
    val chapters: List<ChaptersItem>,
    val name: String,
    val icon: String,
    val id: Int? = null
)

data class LessonsItem(
    val subjectId: Int,
    val name: String,
    val icon: String,
    val id: Int,
    val chapterId: Int,
    val mediaUrl: String
)

data class SubjectsModel(
    val subjects: List<SubjectsItem>,
    val message: String,
    val status: String
)
