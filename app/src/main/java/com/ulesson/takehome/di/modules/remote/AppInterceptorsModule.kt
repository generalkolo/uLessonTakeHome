package com.ulesson.takehome.di.modules.remote

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.ulesson.takehome.di.scopes.AppScope
import com.ulesson.remote.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

@Module
class AppInterceptorsModule {

    @Provides
    @AppScope
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor (
        HttpLoggingInterceptor.Logger { log ->
            Timber.e(log)
        }
    ).apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @AppScope
    internal fun provideStethoLoggingInterceptor(): StethoInterceptor = StethoInterceptor()
}
