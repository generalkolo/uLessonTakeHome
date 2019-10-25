package com.gokada.ng.di.modules.presentation

import androidx.lifecycle.ViewModelProvider
import com.gokada.core.base.BaseViewModel
import com.gokada.ng.auth.presentation.accountsetup.AccountSetupViewModel
import com.gokada.ng.auth.presentation.login.LoginViewModel
import com.gokada.ng.auth.presentation.onboarding.OnBoardingFragmentViewModel
import com.gokada.ng.auth.presentation.onetimepassword.OneTimePasswordViewModel
import com.gokada.ng.di.keys.GokadaSuperAppViewModelKey
import com.gokada.ng.factories.GokadaMainViewModelFactory
import com.gokada.ng.presentation.MainActivityViewModel
import com.gokada.ng.presentation.home.HomeFragmentViewModel
import com.gokada.ng.presentation.splash.SplashActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GokadaSuperAppViewModelModule {

    @Binds
    internal abstract fun bindGokadaViewModelFactory(
        factory: GokadaMainViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @GokadaSuperAppViewModelKey(SplashActivityViewModel::class)
    internal abstract fun bindSplashActivityViewModel(
        viewModel: SplashActivityViewModel
    ): BaseViewModel

    @Binds
    @IntoMap
    @GokadaSuperAppViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(
        viewModel: MainActivityViewModel
    ): BaseViewModel

    @Binds
    @IntoMap
    @GokadaSuperAppViewModelKey(HomeFragmentViewModel::class)
    abstract fun bindHomeFragmentViewModel(
        viewModel: HomeFragmentViewModel
    ): BaseViewModel

    /*
    * Auth Module ViewModels
    */

    @Binds
    @IntoMap
    @GokadaSuperAppViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(
        viewModel: LoginViewModel
    ): BaseViewModel

    @Binds
    @IntoMap
    @GokadaSuperAppViewModelKey(OneTimePasswordViewModel::class)
    abstract fun bindOneTimePasswordViewModel(
        viewModel: OneTimePasswordViewModel
    ): BaseViewModel

    @Binds
    @IntoMap
    @GokadaSuperAppViewModelKey(OnBoardingFragmentViewModel::class)
    abstract fun bindOnboardingViewModel(
        viewModel: OnBoardingFragmentViewModel
    ): BaseViewModel

    @Binds
    @IntoMap
    @GokadaSuperAppViewModelKey(AccountSetupViewModel::class)
    abstract fun bindAccountSetupViewModel(
        viewModel: AccountSetupViewModel
    ): BaseViewModel
}