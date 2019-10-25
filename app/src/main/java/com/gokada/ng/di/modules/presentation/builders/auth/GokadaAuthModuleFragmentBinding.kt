package com.gokada.ng.di.modules.presentation.builders.auth

import com.gokada.ng.auth.presentation.accountsetup.AccountSetupFragment
import com.gokada.ng.auth.presentation.login.LoginFragment
import com.gokada.ng.auth.presentation.onboarding.OnBoardingFragment
import com.gokada.ng.auth.presentation.onetimepassword.OneTimePasswordFragment
import com.gokada.ng.di.scopes.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by edetebenezer on 2019-08-25
 **/
@Module
abstract class GokadaAuthModuleFragmentBinding {

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun bindAccountSetupFragment(): AccountSetupFragment

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun bindLoginFragment(): LoginFragment

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun bindOnBoardingFragment(): OnBoardingFragment

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun bindOneTimePasswordFragment(): OneTimePasswordFragment
}