package com.ulesson.remote.utils

object AppRemoteConstants {

    const val OKHTTP_CACHE = "App Cache"
    const val OKHTTP_CACHE_SIZE = 10 * 1000 * 1000

    const val CLEAN_RETROFIT = "Clean"

    const val CLEAN_OKHTTP = "Clean_Okhttp"

    const val CONNECT_EXCEPTION = "Could not connect to the server. Please check your internet connection and try again."
    const val SOCKET_TIME_OUT_EXCEPTION = "Request timed out while trying to connect. Please ensure you have a strong signal and try again."
    const val UNKNOWN_NETWORK_EXCEPTION = "An unexpected error has occurred. Please check your network connection and try again."
    const val UNKNOWN_HOST_EXCEPTION = "Couldn't connect to the server at the moment. Please try again in a few minutes."
    const val FORBIDDEN_EXCEPTION = "You're not authorised to connect at this moment. Please contact support."
    const val PROTOCOL_EXCEPTION = "An error occurred while connecting to the server. Please try again."
}
