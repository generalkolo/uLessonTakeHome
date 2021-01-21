package com.ulesson.local.mappers

import com.ulesson.local.models.auth.ChaptersLocalItem
import com.ulesson.local.models.auth.LessonsLocalItem
import com.ulesson.local.models.auth.SubjectsLocalItem
import com.ulesson.repository.models.ChaptersRepositoryItem
import com.ulesson.repository.models.LessonsRepositoryItem
import com.ulesson.repository.models.SubjectsRepositoryItem

fun SubjectsRepositoryItem.toSubjectLocalModel(): SubjectsLocalItem =
    SubjectsLocalItem(
        id = id,
        name = name,
        icon = icon,
        chapters = chapters.map { it.toChaptersLocalItem() }
    )

fun ChaptersRepositoryItem.toChaptersLocalItem(): ChaptersLocalItem =
    ChaptersLocalItem(
        name,
        id,
        lessons = lessons.map { it.toLessonsLocalItem() }
    )

fun LessonsRepositoryItem.toLessonsLocalItem(): LessonsLocalItem =
    LessonsLocalItem(
        subjectId,
        name,
        icon,
        id,
        chapterId,
        mediaUrl
    )

// -----------------------------------------------------------------------

fun SubjectsLocalItem.toSubjectRepositoryModel(): SubjectsRepositoryItem =
    SubjectsRepositoryItem(
        id = id,
        name = name,
        icon = icon,
        chapters = chapters.map { it.toChaptersRepositoryItem() }
    )

fun ChaptersLocalItem.toChaptersRepositoryItem(): ChaptersRepositoryItem =
    ChaptersRepositoryItem(
        name,
        id,
        lessons = lessons.map { it.toLessonsLocalItem() }
    )

fun LessonsLocalItem.toLessonsLocalItem(): LessonsRepositoryItem =
    LessonsRepositoryItem(
        subjectId,
        name,
        icon,
        id,
        chapterId,
        mediaUrl
    )
