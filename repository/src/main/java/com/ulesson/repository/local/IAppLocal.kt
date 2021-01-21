package com.ulesson.repository.local

import com.ulesson.repository.models.BaseRepositoryModel
import com.ulesson.repository.models.SubjectRepositoryModel
import io.reactivex.Observable

interface IAppLocal {
    fun getSubjectInformation(): Observable<BaseRepositoryModel<SubjectRepositoryModel>>

    fun saveSubjectInformation(subjectInfo: SubjectRepositoryModel)
}
