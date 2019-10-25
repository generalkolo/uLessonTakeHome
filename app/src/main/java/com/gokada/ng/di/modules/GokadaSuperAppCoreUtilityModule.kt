package com.gokada.ng.di.modules

import android.content.Context
import com.gokada.core.utils.GokadaRxExecutionThread
import com.gokada.domain.utils.RxExecutionThread
import com.gokada.ng.BaseApplication
import com.gokada.ng.di.modules.local.GokadaSuperAppLocalModule
import com.gokada.ng.di.modules.local.GokadaSuperAppRoomModule
import com.gokada.ng.di.modules.preference.GokadaSuperAppPreferenceModule
import com.gokada.ng.di.modules.presentation.GokadaSuperAppActivityBinding
import com.gokada.ng.di.modules.presentation.GokadaSuperAppNavigationBinding
import com.gokada.ng.di.modules.presentation.GokadaSuperAppViewModelModule
import com.gokada.ng.di.modules.remote.GokadaSuperAppRemoteModule
import com.gokada.ng.di.modules.repository.GokadaSuperAppRepositoryModule
import com.gokada.ng.di.modules.services.GokadaSuperAppServiceModules
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        GokadaSuperAppRemoteModule::class,
        GokadaSuperAppRepositoryModule::class,
        GokadaSuperAppPreferenceModule::class,
        GokadaSuperAppLocalModule::class,
        GokadaSuperAppViewModelModule::class,
        GokadaSuperAppActivityBinding::class,
        GokadaSuperAppRoomModule::class,
        GokadaSuperAppNavigationBinding::class,
        GokadaSuperAppExecutorModule::class,
        GokadaSuperAppServiceModules::class
    ]
)
abstract class GokadaSuperAppCoreUtilityModule {

    @Binds
    internal abstract fun bindRxExecutionThread(
        executionThread: GokadaRxExecutionThread
    ): RxExecutionThread

    @Binds
    internal abstract fun bindContext(
        superApp: BaseApplication
    ): Context
}