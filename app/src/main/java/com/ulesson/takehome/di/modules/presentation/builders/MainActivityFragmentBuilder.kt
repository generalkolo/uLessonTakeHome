package com.ulesson.takehome.di.modules.presentation.builders

import com.ulesson.takehome.presentation.dashboard.DashboardFragment
import com.ulesson.takehome.presentation.subjects.SubjectsFragment
import com.ulesson.takehome.presentation.video.VideoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    internal abstract fun bindSubjectsFragment(): SubjectsFragment

    @ContributesAndroidInjector
    internal abstract fun bindVideoFragment(): VideoFragment
}
