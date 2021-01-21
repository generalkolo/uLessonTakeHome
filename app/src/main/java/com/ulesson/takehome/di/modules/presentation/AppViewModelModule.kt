package com.ulesson.takehome.di.modules.presentation

import androidx.lifecycle.ViewModelProvider
import com.ulesson.core.base.BaseViewModel
import com.ulesson.takehome.di.keys.AppViewModelKey
import com.ulesson.takehome.factories.AppMainViewModelFactory
import com.ulesson.takehome.presentation.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelModule {

    @Binds
    internal abstract fun bindAppViewModelFactory(
        factory: AppMainViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @AppViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(
        viewModel: MainActivityViewModel
    ): BaseViewModel
}
