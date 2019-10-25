package com.gokada.core.navigation

import android.content.Context
import android.content.Intent
import javax.inject.Inject

class GokadaSuperAppNavigator @Inject constructor(
    private val gokadaSuperAppIntentResolvers: GokadaSuperAppIntentResolverMap
) {

    fun createIntent (context: Context, intentKey: GokadaSuperAppIntentKey): Intent {
        val resolver = gokadaSuperAppIntentResolvers[intentKey::class.java]?.get() as GokadaSuperAppIntentResolver<GokadaSuperAppIntentKey>?
        return resolver?.getIntent(context, intentKey) ?: throw IllegalArgumentException("Cannot resolve intent key $intentKey")
    }
}