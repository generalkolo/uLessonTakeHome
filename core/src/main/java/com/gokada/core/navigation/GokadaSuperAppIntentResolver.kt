package com.gokada.core.navigation

import android.content.Context
import android.content.Intent

interface GokadaSuperAppIntentResolver <in KEY: GokadaSuperAppIntentKey> {

    fun getIntent(context: Context, key: KEY?): Intent
}