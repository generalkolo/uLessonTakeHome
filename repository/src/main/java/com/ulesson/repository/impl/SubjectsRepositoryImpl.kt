package com.ulesson.repository.impl

import com.ulesson.domain.models.BaseDomainModel
import com.ulesson.domain.models.SubjectsModel
import com.ulesson.domain.repository.SubjectRepository
import com.ulesson.repository.local.IAppLocal
import com.ulesson.repository.mappers.toSubjectsModel
import com.ulesson.repository.remote.ISubjectsRemote
import io.reactivex.Observable
import javax.inject.Inject

class SubjectsRepositoryImpl @Inject constructor(
    private val subjectRemote: ISubjectsRemote,
    private val appLocal: IAppLocal
) : SubjectRepository {
    override fun getSubjectsInformation(): Observable<BaseDomainModel<SubjectsModel>> {
        subjectRemote.getSubjectsInformation()
        return appLocal.getSubjectInformation().map {
            BaseDomainModel(
                successful = it.successful,
                message = it.message,
                data = it.data?.toSubjectsModel()
            )
        }
    }
}
