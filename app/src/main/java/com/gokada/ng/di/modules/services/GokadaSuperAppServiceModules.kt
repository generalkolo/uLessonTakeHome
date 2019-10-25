package com.gokada.ng.di.modules.services

import com.gokada.ng.di.scopes.PerService
import com.gokada.ng.services.GokadaSuperAppFirebaseMessagingService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GokadaSuperAppServiceModules {

    @PerService
    @ContributesAndroidInjector
    internal abstract fun bindGokadaRideHailingService(): GokadaSuperAppFirebaseMessagingService
}