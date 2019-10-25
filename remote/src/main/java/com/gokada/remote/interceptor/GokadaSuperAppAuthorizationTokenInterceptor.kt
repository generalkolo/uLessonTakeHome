package com.gokada.remote.interceptor

import com.gokada.repository.local.auth.IGokadaPreferenceRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * An interceptor that appends the authorization token to each request.
 * @param preferenceRepository is the Repository module implementation
 * of the GokadaSuperApp shared preference
 */
class GokadaSuperAppAuthorizationTokenInterceptor @Inject constructor (
    private val preferenceRepository: IGokadaPreferenceRepository
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val loggedInUserType = preferenceRepository.getLoggedInUserType()
        val newRequest = preferenceRepository.getToken(loggedInUserType)?.let {
            val request = chain.request()
            request.newBuilder().addHeader(
                "Authorization", "Bearer $it"
            ).build()
        } ?: chain.request()


        return chain.proceed(newRequest)
    }
}