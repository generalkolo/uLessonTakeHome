package com.gokada.ng.di.modules.presentation.builders

import com.gokada.ng.presentation.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindHomeFragment(): HomeFragment
}