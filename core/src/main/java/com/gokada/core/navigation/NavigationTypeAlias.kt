package com.gokada.core.navigation

import javax.inject.Provider

typealias GokadaSuperAppIntentResolverMap = @JvmSuppressWildcards Map<Class<out GokadaSuperAppIntentKey>, Provider<GokadaSuperAppIntentResolver<*>>>