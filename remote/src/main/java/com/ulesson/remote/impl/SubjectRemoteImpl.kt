package com.ulesson.remote.impl

import android.util.Log
import com.ulesson.remote.base.BaseNetworkModel
import com.ulesson.remote.mappers.toSubjectsRepositoryModel
import com.ulesson.remote.models.SubjectsRemoteModel
import com.ulesson.remote.services.SubjectService
import com.ulesson.remote.utils.AppRemoteConstants
import com.ulesson.repository.local.IAppLocal
import com.ulesson.repository.remote.ISubjectsRemote
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class SubjectRemoteImpl @Inject constructor(
    @Named(value = AppRemoteConstants.CLEAN_RETROFIT) retrofit: Retrofit,
    private val appLocal: IAppLocal
) : ISubjectsRemote {

    private val subjectService = retrofit.create(SubjectService::class.java)

    override fun getSubjectsInformation() {
        subjectService.getSubjectsInfo()
            .subscribe(object : DisposableSingleObserver<BaseNetworkModel<SubjectsRemoteModel>>() {
                override fun onSuccess(result: BaseNetworkModel<SubjectsRemoteModel>) {
                    result.data?.let {
                        appLocal.saveSubjectInformation(it.subjectsRemoteDataModel?.toSubjectsRepositoryModel()!!)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("An Error Occurred", "Error!!!")
                }
            })
    }
}
