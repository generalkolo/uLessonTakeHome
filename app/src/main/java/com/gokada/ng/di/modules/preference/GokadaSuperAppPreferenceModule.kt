package com.gokada.ng.di.modules.preference

import com.gokada.local.preference.IGokadaPreference
import com.gokada.ng.di.scopes.GokadaSuperAppScope
import com.gokada.preference.impl.GokadaPreferenceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class GokadaSuperAppPreferenceModule {

    @Binds
    @GokadaSuperAppScope
    internal abstract fun bindGokadaSuperAppPreference(
        preference: GokadaPreferenceImpl
    ): IGokadaPreference
}