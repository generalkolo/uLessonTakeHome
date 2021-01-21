package com.ulesson.takehome.di.modules

import android.content.Context
import com.ulesson.takehome.BaseApplication
import com.ulesson.takehome.di.modules.local.AppLocalModule
import com.ulesson.takehome.di.modules.local.AppRoomModule
import com.ulesson.takehome.di.modules.presentation.ActivityBinding
import com.ulesson.takehome.di.modules.presentation.AppViewModelModule
import com.ulesson.takehome.di.modules.remote.AppRemoteModule
import com.ulesson.takehome.di.modules.repository.AppRepositoryModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        AppRemoteModule::class,
        AppRepositoryModule::class,
        AppLocalModule::class,
        AppViewModelModule::class,
        ActivityBinding::class,
        AppRoomModule::class,
        AppExecutorModule::class,
        ExoPlayerModule::class
    ]
)
abstract class AppCoreUtilityModule {

    @Binds
    internal abstract fun bindRxExecutionThread(
        executionThread: com.ulesson.core.utils.RxExecutionThread
    ): com.ulesson.domain.utils.RxExecutionThread

    @Binds
    internal abstract fun bindContext(
        superApp: BaseApplication
    ): Context
}
