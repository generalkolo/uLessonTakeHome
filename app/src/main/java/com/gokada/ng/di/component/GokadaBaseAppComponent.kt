package com.gokada.ng.di.component

import com.gokada.ng.BaseApplication
import com.gokada.ng.di.modules.GokadaSuperAppCoreUtilityModule
import com.gokada.ng.di.scopes.GokadaSuperAppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        GokadaSuperAppCoreUtilityModule::class
    ]
)
@GokadaSuperAppScope
interface GokadaBaseAppComponent : AndroidInjector<DaggerApplication> {

    fun inject(superApp: BaseApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindGokadaBaseAppInstance(superApp: BaseApplication): Builder

        fun buildGokadaBaseAppComponent(): GokadaBaseAppComponent
    }
}