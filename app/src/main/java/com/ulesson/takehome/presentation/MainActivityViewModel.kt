package com.ulesson.takehome.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ulesson.core.base.BaseViewModel
import com.ulesson.core.utils.Event
import com.ulesson.core.utils.state.AppWrapperResource
import com.ulesson.domain.interactors.GetSubjectsUseCase
import com.ulesson.domain.models.LessonsItem
import com.ulesson.domain.models.SubjectsItem
import com.ulesson.domain.models.SubjectsModel
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val getSubjectsUseCase: GetSubjectsUseCase
) : BaseViewModel() {

    private val _subjectsInformation = MutableLiveData<AppWrapperResource<SubjectsModel>>()
    val subjectsInformation =
        _subjectsInformation as LiveData<AppWrapperResource<SubjectsModel>>

    private val _subjectsItem = MutableLiveData<SubjectsItem>()
    val subjectsItem =
        _subjectsItem as LiveData<SubjectsItem>

    private val _lessonItem = MutableLiveData<LessonsItem>()
    val lessonItem =
        _lessonItem as LiveData<LessonsItem>

    private val _navigateToVideoFragment = MutableLiveData<Event<Boolean>>()
    val navigateToVideoFragment =
        _navigateToVideoFragment as LiveData<Event<Boolean>>

    init {
        _subjectsInformation.postValue(AppWrapperResource.loading())
        getSubjectsUseCase.executeUseCaseAndPerformAction({
            _subjectsInformation.postValue(AppWrapperResource.success(it.data))
        }, {
            _subjectsInformation.postValue(AppWrapperResource.failed(it.localizedMessage))
        })
    }

    fun setClickedSubject(item: SubjectsItem) {
        _subjectsItem.postValue(item)
    }

    fun setClickedLesson(item: LessonsItem) {
        _lessonItem.postValue(item)
        _navigateToVideoFragment.postValue(Event(true))
    }

    override fun onCleared() {
        super.onCleared()
        getSubjectsUseCase.dispose()
    }
}
