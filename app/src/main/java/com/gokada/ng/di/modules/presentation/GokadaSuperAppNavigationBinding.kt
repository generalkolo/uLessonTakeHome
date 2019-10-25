package com.gokada.ng.di.modules.presentation

import com.gokada.core.navigation.GokadaSuperAppIntentKey
import com.gokada.core.navigation.GokadaSuperAppIntentResolver
import com.gokada.ng.auth.presentation.AuthenticationActivity
import com.gokada.ng.di.keys.GokadaSuperAppIntentResolverKey
import com.gokada.ng.presentation.MainActivity
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class GokadaSuperAppNavigationBinding {

    @Provides
    @IntoMap
    @GokadaSuperAppIntentResolverKey(GokadaSuperAppIntentKey.Main::class)
    internal fun provideMainActivityIntentKey(): GokadaSuperAppIntentResolver<*> =
        MainActivity

    @Provides
    @IntoMap
    @GokadaSuperAppIntentResolverKey(GokadaSuperAppIntentKey.Authentication::class)
    internal fun provideAuthenticationActivityIntentKey(): GokadaSuperAppIntentResolver<*> =
        AuthenticationActivity
}