package com.gokada.ng.di.modules.local

import com.gokada.local.impl.preference.GokadaPreferenceRepositoryImpl
import com.gokada.ng.di.scopes.GokadaSuperAppScope
import com.gokada.repository.local.auth.IGokadaPreferenceRepository
import dagger.Binds
import dagger.Module

@Module
abstract class GokadaSuperAppLocalModule {

    @Binds
    @GokadaSuperAppScope
    internal abstract fun bindGokadaPreferenceRepository(
        preference: GokadaPreferenceRepositoryImpl
    ): IGokadaPreferenceRepository
}