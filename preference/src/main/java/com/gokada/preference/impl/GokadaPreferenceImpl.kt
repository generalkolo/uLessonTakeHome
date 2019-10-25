package com.gokada.preference.impl

import android.content.Context
import androidx.core.content.edit
import com.gokada.local.preference.IGokadaPreference
import com.gokada.preference.utils.GokadaPreferenceConstants
import javax.inject.Inject

/**
 * Created by ayokunlepaul on 2019-07-17
 */
class GokadaPreferenceImpl @Inject constructor(
    context: Context
): IGokadaPreference {

    private val gokadaSuperAppPreference = context.getSharedPreferences(GokadaPreferenceConstants.GOKADA_RIDER_PREFERENCE, Context.MODE_PRIVATE)

    override fun saveToken(type: Int, token: String) {
        if (type == GokadaPreferenceConstants.TYPE_RIDER) gokadaSuperAppPreference.edit {
            putString(GokadaPreferenceConstants.GOKADA_RIDER_TOKEN, token)
        } else {
            gokadaSuperAppPreference.edit {
                putString(GokadaPreferenceConstants.GOKADA_PILOT_TOKEN, token)
            }
        }
    }

    override fun saveRegistrationStatus(fullyRegistered: Boolean) {
        gokadaSuperAppPreference.edit{
            putBoolean(GokadaPreferenceConstants.GOKADA_IS_USER_FULLY_REGISTERED,fullyRegistered)
            apply()
        }
    }

    override fun isUserFullyRegistered(): Boolean = gokadaSuperAppPreference.getBoolean(GokadaPreferenceConstants.GOKADA_IS_USER_FULLY_REGISTERED, false)

    override fun getToken(type: Int): String? =
        if (type == GokadaPreferenceConstants.TYPE_RIDER) gokadaSuperAppPreference.getString(GokadaPreferenceConstants.GOKADA_RIDER_TOKEN, null)
        else gokadaSuperAppPreference.getString(GokadaPreferenceConstants.GOKADA_PILOT_TOKEN, null)

    override fun saveLoggedInUserType(type: Int) {
        gokadaSuperAppPreference.edit {
            putInt(GokadaPreferenceConstants.GOKADA_LOGGED_IN_USER, type)
            apply()
        }
    }

    override fun getLoggedInUserType(): Int = gokadaSuperAppPreference.getInt(GokadaPreferenceConstants.GOKADA_LOGGED_IN_USER, 0)

    override fun setLoggedIn(type: Int) {
        if (type == GokadaPreferenceConstants.TYPE_RIDER) gokadaSuperAppPreference.edit {
            putBoolean(GokadaPreferenceConstants.GOKADA_LOGIN_TOKEN, true)
        } else {
            gokadaSuperAppPreference.edit {
                putBoolean(GokadaPreferenceConstants.GOKADA_LOGIN_TOKEN, true)
            }
        }
    }

    override fun isLoggedIn(type: Int): Boolean = if (type == GokadaPreferenceConstants.TYPE_RIDER) gokadaSuperAppPreference.getBoolean(GokadaPreferenceConstants.GOKADA_LOGIN_TOKEN, false)
    else gokadaSuperAppPreference.getBoolean(GokadaPreferenceConstants.GOKADA_LOGIN_TOKEN, false)

    override fun logOut(type: Int) = if (type == GokadaPreferenceConstants.TYPE_RIDER) gokadaSuperAppPreference.edit { clear() }
    else gokadaSuperAppPreference.edit { clear() }

    override fun saveValidationToken(token: String) {
        gokadaSuperAppPreference.edit {
            putString(GokadaPreferenceConstants.GOKADA_VALIDATION_TOKEN, token)
            apply()
        }
    }

    override fun getValidationToken(): String? = gokadaSuperAppPreference.getString(GokadaPreferenceConstants.GOKADA_VALIDATION_TOKEN, null)

    override fun hasCompletedWalletSetup(): Boolean = gokadaSuperAppPreference.getBoolean(GokadaPreferenceConstants.HAS_COMPLETED_WALLET_SETUP, false)

    override fun setWalletSetupCompletionStatus(hasCompletedWalletSetup: Boolean) {
        gokadaSuperAppPreference.edit {
            putBoolean(GokadaPreferenceConstants.HAS_COMPLETED_WALLET_SETUP, hasCompletedWalletSetup)
            apply()
        }
    }

    override fun clearUserPreference() = gokadaSuperAppPreference.edit{ clear() }
}