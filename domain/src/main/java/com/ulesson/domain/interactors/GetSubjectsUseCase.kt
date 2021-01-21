package com.ulesson.domain.interactors

import com.ulesson.domain.models.BaseDomainModel
import com.ulesson.domain.models.SubjectsModel
import com.ulesson.domain.repository.SubjectRepository
import com.ulesson.domain.utils.RxExecutionThread
import io.reactivex.Observable
import javax.inject.Inject

class GetSubjectsUseCase @Inject constructor(
    executionThread: RxExecutionThread,
    private val subjectRepository: SubjectRepository
) : ObservableUseCaseNoParam<BaseDomainModel<SubjectsModel>>(executionThread) {

    override fun buildUseCaseObservable(): Observable<BaseDomainModel<SubjectsModel>> =
        subjectRepository.getSubjectsInformation()
}
