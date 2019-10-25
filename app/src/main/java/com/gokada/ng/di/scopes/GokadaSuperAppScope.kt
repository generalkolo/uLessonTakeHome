package com.gokada.ng.di.scopes

import javax.inject.Scope

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@Scope
annotation class GokadaSuperAppScope