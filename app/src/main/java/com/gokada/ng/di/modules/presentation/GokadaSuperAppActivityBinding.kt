package com.gokada.ng.di.modules.presentation

import com.gokada.ng.auth.presentation.AuthenticationActivity
import com.gokada.ng.di.modules.presentation.builders.MainActivityFragmentBuilder
import com.gokada.ng.di.modules.presentation.builders.auth.GokadaAuthModuleFragmentBinding
import com.gokada.ng.di.scopes.PerActivity
import com.gokada.ng.presentation.MainActivity
import com.gokada.ng.presentation.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GokadaSuperAppActivityBinding {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityFragmentBuilder::class])
    internal abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun bindSplashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector(
        modules = [GokadaAuthModuleFragmentBinding::class]
    )
    internal abstract fun bindAuthActivity(): AuthenticationActivity
}