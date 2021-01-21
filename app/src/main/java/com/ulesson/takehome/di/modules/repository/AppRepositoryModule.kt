package com.ulesson.takehome.di.modules.repository

import com.ulesson.domain.repository.SubjectRepository
import com.ulesson.takehome.di.scopes.AppScope
import com.ulesson.repository.impl.SubjectsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppRepositoryModule {

    @Binds
    @AppScope
    internal abstract fun bindSubjectRepository(
        repository: SubjectsRepositoryImpl
    ): SubjectRepository

}
