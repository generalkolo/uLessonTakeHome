package com.gokada.ng.di.modules.remote

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.gokada.ng.di.scopes.GokadaSuperAppScope
import com.gokada.remote.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

@Module
class GokadaSuperAppInterceptorsModule {

    @Provides
    @GokadaSuperAppScope
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor (
        HttpLoggingInterceptor.Logger { log ->
            Timber.e(log)
        }
    ).apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @GokadaSuperAppScope
    internal fun provideStethoLoggingInterceptor(): StethoInterceptor = StethoInterceptor()
}