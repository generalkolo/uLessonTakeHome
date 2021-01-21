package com.ulesson.domain.repository

import com.ulesson.domain.models.BaseDomainModel
import com.ulesson.domain.models.SubjectsModel
import io.reactivex.Observable

interface SubjectRepository {
    fun getSubjectsInformation(): Observable<BaseDomainModel<SubjectsModel>>
}
