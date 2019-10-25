package com.gokada.remote.interceptor

import com.gokada.repository.local.auth.IGokadaPreferenceRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * This interceptor intercepts every request and appends the
 * validation token to the header.
 * This is a necessary requirement from the backend.
 */
class GokadaSuperAppValidationTokenInterceptor @Inject constructor(
    private val preferenceRepository: IGokadaPreferenceRepository
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = preferenceRepository.getValidationToken()?.let {
            val  originalRequest = chain.request()
            originalRequest.newBuilder().addHeader(
                "token", it
            ).build()
        } ?: chain.request()

        return chain.proceed(newRequest)
    }
}