package com.ulesson.local.models.auth

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Subjects Information"
)
data class SubjectsLocalItem(
    @PrimaryKey
    val id: Int,
    val chapters: List<ChaptersLocalItem>,
    val name: String,
    val icon: String
)

data class ChaptersLocalItem(
    val name: String,
    val id: Int,
    val lessons: List<LessonsLocalItem>
)

data class LessonsLocalItem(
    val subjectId: Int,
    val name: String,
    val icon: String,
    val id: Int,
    val chapterId: Int,
    val mediaUrl: String
)
