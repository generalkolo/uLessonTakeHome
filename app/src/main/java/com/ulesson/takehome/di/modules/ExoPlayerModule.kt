package com.ulesson.takehome.di.modules

import android.content.Context
import com.ulesson.takehome.BaseApplication
import com.ulesson.takehome.di.scopes.AppScope
import com.ulesson.takehome.utils.constants.AppConstants.USER_AGENT
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import dagger.Module
import dagger.Provides

@Module(
    includes = [ExoPlayerUtilsModule::class]
)
class ExoPlayerModule {
    @AppScope
    @Provides
    fun providesExoPlayer(
        context: BaseApplication,
        defaultRendersFactory: DefaultRenderersFactory,
        defaultTrackSelector: DefaultTrackSelector
    ): SimpleExoPlayer {
        return ExoPlayerFactory.newSimpleInstance(
            context,
            defaultRendersFactory,
            defaultTrackSelector
        )
    }
}

@Module
class ExoPlayerUtilsModule {
    @Provides
    internal fun providesRenderFactory(context: Context) =
        DefaultRenderersFactory(context)

    @Provides
    internal fun providesTrackSelector() = DefaultTrackSelector()

    @Provides
    internal fun providesDataSourceFactory() = DefaultHttpDataSourceFactory(
        USER_AGENT,
        DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
        DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
        true
    )

    @Provides
    internal fun providesDefaultDataSourceFactory(context: Context) = DefaultDataSourceFactory(
        context,
        USER_AGENT
    )

    @Provides
    internal fun providesProgressiveMediaSource(defaultHttpDataSourceFactory: DefaultHttpDataSourceFactory) =
        ProgressiveMediaSource.Factory(defaultHttpDataSourceFactory)

    @Provides
    internal fun providesMediaSource(defaultDataSourceFactory: DefaultDataSourceFactory) =
        ExtractorMediaSource.Factory(defaultDataSourceFactory)
}
