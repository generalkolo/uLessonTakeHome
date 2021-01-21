package com.ulesson.takehome.di.modules.presentation

import com.ulesson.takehome.di.modules.presentation.builders.MainActivityFragmentBuilder
import com.ulesson.takehome.di.scopes.PerActivity
import com.ulesson.takehome.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinding {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityFragmentBuilder::class])
    internal abstract fun bindMainActivity(): MainActivity
}
