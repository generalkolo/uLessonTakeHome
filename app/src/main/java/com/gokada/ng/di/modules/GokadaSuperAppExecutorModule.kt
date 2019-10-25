package com.gokada.ng.di.modules

import com.gokada.ng.di.scopes.GokadaSuperAppScope
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by edetebenezer on 2019-09-17
 **/
@Module
class GokadaSuperAppExecutorModule {

    @GokadaSuperAppScope
    @Provides
    fun getExecutor(): Executor {
        return Executors.newFixedThreadPool(2)
    }
}