package com.ulesson.repository.mappers

import com.ulesson.domain.models.ChaptersItem
import com.ulesson.domain.models.LessonsItem
import com.ulesson.domain.models.SubjectsItem
import com.ulesson.domain.models.SubjectsModel
import com.ulesson.repository.models.ChaptersRepositoryItem
import com.ulesson.repository.models.LessonsRepositoryItem
import com.ulesson.repository.models.SubjectRepositoryModel
import com.ulesson.repository.models.SubjectsRepositoryItem

fun SubjectRepositoryModel.toSubjectsModel(): SubjectsModel =
    SubjectsModel(
        message = message,
        status = status,
        subjects = subjects.map { it.toSubjectsItem() }
    )

fun SubjectsRepositoryItem.toSubjectsItem(): SubjectsItem =
    SubjectsItem(
        name = name,
        icon = icon,
        id = id,
        chapters = chapters.map { it.toChaptersItem() }
    )

fun ChaptersRepositoryItem.toChaptersItem(): ChaptersItem =
    ChaptersItem(
        name = name,
        id = id,
        lessons = lessons.map { it.toLessonsItem() }
    )

fun LessonsRepositoryItem.toLessonsItem(): LessonsItem =
    LessonsItem(
        subjectId = subjectId,
        name = name,
        icon = icon,
        id = id,
        chapterId = chapterId,
        mediaUrl = mediaUrl
    )

