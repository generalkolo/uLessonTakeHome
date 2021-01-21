package com.ulesson.takehome.di.modules.remote

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.ulesson.remote.interceptor.responseinterceptor.AuthenticationResponseInterceptor
import com.ulesson.remote.utils.AppRemoteConstants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module (
    includes = [
        AppInterceptorsModule::class
    ]
)
class AppOkhttpModule {

    @Provides
    internal fun provideFile(context: Context): File = File (
        context.cacheDir, AppRemoteConstants.OKHTTP_CACHE
    )

    @Provides
    internal fun provideCache(file: File): Cache = Cache (
        file, AppRemoteConstants.OKHTTP_CACHE_SIZE.toLong()
    )

    @Provides
    @Named(value = AppRemoteConstants.CLEAN_OKHTTP)
    internal fun provideCleanOkhttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authenticationResponseInterceptor: AuthenticationResponseInterceptor,
        stethoInterceptor: StethoInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(authenticationResponseInterceptor)
            addNetworkInterceptor(stethoInterceptor)
            cache(cache)
            followRedirects(true)
            followSslRedirects(true)
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()
    }
}
