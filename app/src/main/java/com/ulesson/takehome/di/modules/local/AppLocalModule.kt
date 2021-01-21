package com.ulesson.takehome.di.modules.local

import com.ulesson.local.impl.AppLocalImpl
import com.ulesson.takehome.di.scopes.AppScope
import com.ulesson.repository.local.IAppLocal
import dagger.Binds
import dagger.Module

@Module
abstract class AppLocalModule {

    @Binds
    @AppScope
    internal abstract fun bindWalletLocal(
        local: AppLocalImpl
    ): IAppLocal
}
