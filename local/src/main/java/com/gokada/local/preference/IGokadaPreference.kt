package com.gokada.local.preference

/**
 * Created by ayokunlepaul on 2019-07-17
 */
interface IGokadaPreference {

    fun saveToken(type: Int, token: String)

    fun getToken(type: Int): String?

    fun saveLoggedInUserType(type: Int)

    fun getLoggedInUserType(): Int

    fun setLoggedIn(type: Int)

    fun isLoggedIn(type: Int): Boolean

    fun logOut(type: Int)

    fun saveValidationToken(token: String)

    fun getValidationToken(): String?

    fun isUserFullyRegistered(): Boolean

    fun saveRegistrationStatus(fullyRegistered: Boolean)

    fun hasCompletedWalletSetup(): Boolean

    fun setWalletSetupCompletionStatus(hasCompletedWalletSetup: Boolean)

    fun clearUserPreference()
}