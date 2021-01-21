package com.ulesson.takehome.di.modules

import com.ulesson.takehome.di.scopes.AppScope
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Module
class AppExecutorModule {

    @AppScope
    @Provides
    fun getExecutor(): Executor {
        return Executors.newFixedThreadPool(2)
    }
}
