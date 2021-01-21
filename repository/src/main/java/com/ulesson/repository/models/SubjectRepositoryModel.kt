package com.ulesson.repository.models

data class ChaptersRepositoryItem(
    val name: String,
    val id: Int,
    val lessons: List<LessonsRepositoryItem>
)

data class SubjectsRepositoryItem(
    val chapters: List<ChaptersRepositoryItem>,
    val name: String,
    val icon: String,
    val id: Int
)

data class LessonsRepositoryItem(
    val subjectId: Int,
    val name: String,
    val icon: String,
    val id: Int,
    val chapterId: Int,
    val mediaUrl: String
)

data class SubjectRepositoryModel(
    val subjects: List<SubjectsRepositoryItem>,
    val message: String,
    val status: String
)

