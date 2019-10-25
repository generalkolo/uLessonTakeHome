package com.gokada.local.impl.preference

import com.gokada.local.preference.IGokadaPreference
import com.gokada.repository.local.auth.IGokadaPreferenceRepository
import javax.inject.Inject

class GokadaPreferenceRepositoryImpl @Inject constructor(
    private val preference: IGokadaPreference
) : IGokadaPreferenceRepository {

    override fun isUserFullyRegistered(): Boolean = preference.isUserFullyRegistered()

    override fun saveFullyRegisteredStatus(fullyRegistered: Boolean) =
        preference.saveRegistrationStatus(fullyRegistered)

    override fun saveToken(type: Int, token: String) = preference.saveToken(
        type = type,
        token = token
    )

    override fun getToken(type: Int): String? = preference.getToken(type)

    override fun saveLoggedInUserType(type: Int) = preference.saveLoggedInUserType(type)

    override fun getLoggedInUserType(): Int = preference.getLoggedInUserType()

    override fun setLoggedIn(type: Int) = preference.setLoggedIn(type)

    override fun isLoggedIn(type: Int): Boolean = preference.isLoggedIn(type)

    override fun logOut(type: Int) = preference.logOut(type)

    override fun saveValidationToken(token: String) = preference.saveValidationToken(token)

    override fun getValidationToken(): String? = preference.getValidationToken()

    override fun clearPreference()= preference.clearUserPreference()
}