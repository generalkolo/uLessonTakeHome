package com.gokada.remote.interceptor

import com.gokada.remote.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * An interceptor that appends the authorization token to each request.
 * of the GokadaSuperApp shared preference
 */
class GokadaSuperAppWalletAndPaymentAuthorizationTokenInterceptor @Inject constructor (): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder().addHeader(
                "Authorization", BuildConfig.WALLET_PAYMENT_API_AUTHORIZATION_TOKEN
            ).build()

        return chain.proceed(newRequest)
    }
}