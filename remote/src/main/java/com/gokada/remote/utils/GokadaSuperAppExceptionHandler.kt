package com.gokada.remote.utils

import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.*

object GokadaSuperAppExceptionHandler {

    fun getErrorFromThrowable(throwable: Throwable): String {
        return when (throwable) {
            is ConnectException -> GokadaSuperAppRemoteConstants.CONNECT_EXCEPTION
            is UnknownHostException -> GokadaSuperAppRemoteConstants.UNKNOWN_HOST_EXCEPTION
            is SocketTimeoutException -> GokadaSuperAppRemoteConstants.SOCKET_TIME_OUT_EXCEPTION
            is UnknownServiceException -> throwable.localizedMessage
            is ProtocolException -> GokadaSuperAppRemoteConstants.PROTOCOL_EXCEPTION
            is HttpException -> {
                return when {
                    throwable.code() == 403 -> {
                        val message = throwable.message()
                        return message ?: GokadaSuperAppRemoteConstants.FORBIDDEN_EXCEPTION
                    }
                    throwable.code() == 204 -> {
                        val message = throwable.message()
                        return message ?: GokadaSuperAppRemoteConstants.FORBIDDEN_EXCEPTION
                    }
                    else -> try {
                        val response = throwable.response()
                        val json = JSONObject(response?.errorBody()?.string())
                        json.getString("message")
                    } catch (e1: JSONException) {
                        GokadaSuperAppRemoteConstants.UNKNOWN_NETWORK_EXCEPTION
                    } catch (e1: IOException) {
                        GokadaSuperAppRemoteConstants.UNKNOWN_NETWORK_EXCEPTION
                    }
                }
            }
            else -> GokadaSuperAppRemoteConstants.UNKNOWN_NETWORK_EXCEPTION
        }
    }
}