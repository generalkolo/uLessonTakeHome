package com.gokada.repository.local.auth

interface IGokadaPreferenceRepository {

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

    fun saveFullyRegisteredStatus(fullyRegistered: Boolean = false)

    fun clearPreference()
}