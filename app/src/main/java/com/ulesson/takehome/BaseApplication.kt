package com.ulesson.takehome

import android.content.Context
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.ulesson.core.utils.BaseAppLog
import com.ulesson.takehome.di.component.DaggerAppComponent
import com.ulesson.takehome.di.component.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

    private lateinit var baseAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        BaseAppLog.init()
        Stetho.initializeWithDefaults(this)
        baseAppComponent.inject(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        baseAppComponent = DaggerAppComponent.builder()
            .bindAppInstance(this)
            .buildAppComponent()

        return baseAppComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
