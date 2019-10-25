package com.gokada.ng

import android.content.Context
import androidx.multidex.MultiDex
import com.crashlytics.android.Crashlytics
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.stetho.Stetho
import com.gokada.core.utils.BaseAppLog
import com.gokada.ng.di.component.DaggerGokadaBaseAppComponent
import com.gokada.ng.di.component.GokadaBaseAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.fabric.sdk.android.Fabric

class BaseApplication : DaggerApplication() {

    private lateinit var baseAppComponent: GokadaBaseAppComponent

    override fun onCreate() {
        super.onCreate()
        BaseAppLog.init()
        Stetho.initializeWithDefaults(this)
        baseAppComponent.inject(this)
        Fabric.with(this, Crashlytics())
        Fresco.initialize(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        baseAppComponent = DaggerGokadaBaseAppComponent.builder()
            .bindGokadaBaseAppInstance(this)
            .buildGokadaBaseAppComponent()

        return baseAppComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}