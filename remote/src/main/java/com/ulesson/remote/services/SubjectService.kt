package com.ulesson.remote.services

import com.ulesson.remote.base.BaseNetworkModel
import com.ulesson.remote.models.SubjectsRemoteModel
import io.reactivex.Single
import retrofit2.http.GET

interface SubjectService {
    @GET("3p/api/content/grade")
    fun getSubjectsInfo(): Single<BaseNetworkModel<SubjectsRemoteModel>>
}
