package com.gokada.ng.di.modules.remote

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.gokada.remote.interceptor.GokadaSuperAppAuthorizationTokenInterceptor
import com.gokada.remote.interceptor.GokadaSuperAppValidationTokenInterceptor
import com.gokada.remote.interceptor.GokadaSuperAppWalletAndPaymentAuthorizationTokenInterceptor
import com.gokada.remote.interceptor.responseinterceptor.AuthenticationResponseInterceptor
import com.gokada.remote.utils.GokadaSuperAppRemoteConstants
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
        GokadaSuperAppInterceptorsModule::class
    ]
)
class GokadaSuperAppOkhttpModule {

    @Provides
    internal fun provideFile(context: Context): File = File (
        context.cacheDir, GokadaSuperAppRemoteConstants.OKHTTP_CACHE
    )

    @Provides
    internal fun provideCache(file: File): Cache = Cache (
        file, GokadaSuperAppRemoteConstants.OKHTTP_CACHE_SIZE.toLong()
    )

    @Provides
    @Named(value = GokadaSuperAppRemoteConstants.NON_WALLET_PAYMENT_OKHTTP)
    internal fun provideNonWalletAndPaymentOkhttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        validationTokenInterceptor: GokadaSuperAppValidationTokenInterceptor,
        authorizationTokenInterceptor: GokadaSuperAppAuthorizationTokenInterceptor,
        authenticationResponseInterceptor: AuthenticationResponseInterceptor,
        stethoInterceptor: StethoInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(validationTokenInterceptor)
            addInterceptor(authorizationTokenInterceptor)
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

    @Provides
    @Named(value = GokadaSuperAppRemoteConstants.WALLET_PAYMENT_OKHTTP)
    internal fun provideWalletAndPaymentOkhttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authorizationTokenInterceptor: GokadaSuperAppWalletAndPaymentAuthorizationTokenInterceptor,
        authenticationResponseInterceptor: AuthenticationResponseInterceptor,
        stethoInterceptor: StethoInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(authorizationTokenInterceptor)
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

    @Provides
    @Named(value = GokadaSuperAppRemoteConstants.CLEAN_OKHTTP)
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