package com.ulesson.takehome.di.component

import com.ulesson.takehome.BaseApplication
import com.ulesson.takehome.di.modules.AppCoreUtilityModule
import com.ulesson.takehome.di.scopes.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppCoreUtilityModule::class
    ]
)
@AppScope
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(superApp: BaseApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindAppInstance(superApp: BaseApplication): Builder

        fun buildAppComponent(): AppComponent
    }
}
