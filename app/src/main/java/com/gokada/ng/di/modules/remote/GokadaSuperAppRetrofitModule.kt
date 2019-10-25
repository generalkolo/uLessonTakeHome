package com.gokada.ng.di.modules.remote

import com.gokada.remote.BuildConfig
import com.gokada.remote.utils.GokadaSuperAppRemoteConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module(
    includes = [
        GokadaSuperAppOkhttpModule::class,
        GokadaSuperAppFactoriesModule::class
    ]
)
class GokadaSuperAppRetrofitModule {

    @Provides
    @Named(value = GokadaSuperAppRemoteConstants.RIDE_HAILING_RETROFIT)
    internal fun provideRideHailingRetrofit(
        @Named(value = GokadaSuperAppRemoteConstants.NON_WALLET_PAYMENT_OKHTTP) okHttpClient: OkHttpClient,
        callAdapterFactory: RxJava2CallAdapterFactory,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().apply {
        client(okHttpClient)
        addCallAdapterFactory(callAdapterFactory)
        addConverterFactory(converterFactory)
        baseUrl(BuildConfig.BASE_URL_RIDE_HAILING)
    }.build()

    @Provides
    @Named(value = GokadaSuperAppRemoteConstants.TOKEN_RETROFIT)
    internal fun provideTokenRetrofit(
        @Named(value = GokadaSuperAppRemoteConstants.NON_WALLET_PAYMENT_OKHTTP) okHttpClient: OkHttpClient,
        callAdapterFactory: RxJava2CallAdapterFactory,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().apply {
        client(okHttpClient)
        addCallAdapterFactory(callAdapterFactory)
        addConverterFactory(converterFactory)
        baseUrl(BuildConfig.BASE_URL_WALLET)
    }.build()


    @Provides
    @Named(value = GokadaSuperAppRemoteConstants.WALLET_RETROFIT)
    internal fun provideWalletRetrofit(
        @Named(value = GokadaSuperAppRemoteConstants.WALLET_PAYMENT_OKHTTP) okHttpClient: OkHttpClient,
        callAdapterFactory: RxJava2CallAdapterFactory,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().apply {
        client(okHttpClient)
        addCallAdapterFactory(callAdapterFactory)
        addConverterFactory(converterFactory)
        baseUrl(BuildConfig.BASE_URL_WALLET)
    }.build()

    @Provides
    @Named(value = GokadaSuperAppRemoteConstants.PAYMENT_RETROFIT)
    internal fun providePaymentRetrofit(
        @Named(value = GokadaSuperAppRemoteConstants.WALLET_PAYMENT_OKHTTP) okHttpClient: OkHttpClient,
        callAdapterFactory: RxJava2CallAdapterFactory,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().apply {
        client(okHttpClient)
        addCallAdapterFactory(callAdapterFactory)
        addConverterFactory(converterFactory)
        baseUrl(BuildConfig.BASE_URL_PAYMENT)
    }.build()

    @Provides
    @Named(value = GokadaSuperAppRemoteConstants.CLEAN_RETROFIT)
    internal fun provideCleanRetrofit(
        @Named(value = GokadaSuperAppRemoteConstants.CLEAN_OKHTTP) okHttpClient: OkHttpClient,
        callAdapterFactory: RxJava2CallAdapterFactory,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().apply {
        client(okHttpClient)
        addCallAdapterFactory(callAdapterFactory)
        addConverterFactory(converterFactory)
        baseUrl(BuildConfig.BASE_URL_PAYMENT)
    }.build()

}