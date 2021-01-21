package com.ulesson.takehome.di.modules.remote

import com.ulesson.takehome.di.scopes.AppScope
import com.ulesson.remote.impl.SubjectRemoteImpl
import com.ulesson.repository.remote.ISubjectsRemote
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        AppRetrofitModule::class
    ]
)
abstract class AppRemoteModule {

    @Binds
    @AppScope
    internal abstract fun bindSubjectRemote(
        remote: SubjectRemoteImpl
    ): ISubjectsRemote

}
