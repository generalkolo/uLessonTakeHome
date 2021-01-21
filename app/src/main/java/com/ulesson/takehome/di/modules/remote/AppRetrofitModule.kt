package com.ulesson.takehome.di.modules.remote

import com.ulesson.remote.BuildConfig
import com.ulesson.remote.utils.AppRemoteConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module(
    includes = [
        AppOkhttpModule::class,
        AppFactoriesModule::class
    ]
)
class AppRetrofitModule {

    @Provides
    @Named(value = AppRemoteConstants.CLEAN_RETROFIT)
    internal fun provideCleanRetrofit(
        @Named(value = AppRemoteConstants.CLEAN_OKHTTP) okHttpClient: OkHttpClient,
        callAdapterFactory: RxJava2CallAdapterFactory,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().apply {
        client(okHttpClient)
        addCallAdapterFactory(callAdapterFactory)
        addConverterFactory(converterFactory)
        baseUrl(BuildConfig.BASE_URL)
    }.build()

}
