package com.gokada.preference

/**
 * Created by ayokunlepaul on 2019-07-17
 */
interface PreferenceMapper <P, L> {

    fun mapToPreference(local: L): P

    fun mapToLocal(preference: P): L
}