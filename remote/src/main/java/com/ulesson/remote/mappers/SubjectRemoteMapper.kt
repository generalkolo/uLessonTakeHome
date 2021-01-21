package com.ulesson.remote.mappers

import com.ulesson.remote.models.ChaptersRemoteItem
import com.ulesson.remote.models.LessonsRemoteItem
import com.ulesson.remote.models.SubjectsRemoteDataModel
import com.ulesson.remote.models.SubjectsRemoteItem
import com.ulesson.remote.utils.safeInt
import com.ulesson.remote.utils.safeString
import com.ulesson.repository.models.ChaptersRepositoryItem
import com.ulesson.repository.models.LessonsRepositoryItem
import com.ulesson.repository.models.SubjectRepositoryModel
import com.ulesson.repository.models.SubjectsRepositoryItem

fun SubjectsRemoteDataModel.toSubjectsRepositoryModel(): SubjectRepositoryModel =
    SubjectRepositoryModel(
        message = message.safeString(),
        status = status.safeString(),
        subjects = subjects?.map { it.toSubjectsRepositoryItem() } ?: emptyList()
    )

fun SubjectsRemoteItem.toSubjectsRepositoryItem(): SubjectsRepositoryItem =
    SubjectsRepositoryItem(
        name = name.safeString(),
        icon = icon.safeString(),
        id = id.safeInt(),
        chapters = chapters?.map { it.toChaptersRepositoryItem() } ?: emptyList()
    )

fun ChaptersRemoteItem.toChaptersRepositoryItem(): ChaptersRepositoryItem =
    ChaptersRepositoryItem(
        name = name.safeString(),
        id = id.safeInt(),
        lessons = lessons?.map { it.toLessonsRepositoryItem() } ?: emptyList()
    )

fun LessonsRemoteItem.toLessonsRepositoryItem(): LessonsRepositoryItem =
    LessonsRepositoryItem(
        subjectId = subjectId.safeInt(),
        name = name.safeString(),
        icon = icon.safeString(),
        id = id.safeInt(),
        chapterId = chapterId.safeInt(),
        mediaUrl = mediaUrl.safeString()
    )
