package com.ulesson.local.impl

import com.ulesson.local.mappers.toSubjectLocalModel
import com.ulesson.local.mappers.toSubjectRepositoryModel
import com.ulesson.local.room.dao.SubjectInfoDao
import com.ulesson.repository.local.IAppLocal
import com.ulesson.repository.models.BaseRepositoryModel
import com.ulesson.repository.models.SubjectRepositoryModel
import io.reactivex.Observable
import javax.inject.Inject

class AppLocalImpl @Inject constructor(
    private val subjectInformationDao: SubjectInfoDao
) : IAppLocal {
    override fun getSubjectInformation(): Observable<BaseRepositoryModel<SubjectRepositoryModel>> {
        return subjectInformationDao.getSubjectInfo().map {
            if (it.isEmpty()) BaseRepositoryModel(
                successful = true,
                message = "No Subject Information Available"
            ) else BaseRepositoryModel(
                successful = true,
                data = SubjectRepositoryModel(
                    subjects = it.map { it.toSubjectRepositoryModel() },
                    message = "Success",
                    status = "Success"
                )
            )
        }
    }

    override fun saveSubjectInformation(subjectInfo: SubjectRepositoryModel) {
        subjectInformationDao.saveSubjectInfo(subjectInfo.subjects.map { it.toSubjectLocalModel() })
    }
}
