package com.gokada.ng.di.keys

import com.gokada.core.navigation.GokadaSuperAppIntentKey
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class GokadaSuperAppIntentResolverKey (val value: KClass<out GokadaSuperAppIntentKey>)