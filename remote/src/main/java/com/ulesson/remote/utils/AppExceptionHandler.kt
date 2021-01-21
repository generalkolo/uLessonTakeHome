package com.ulesson.remote.utils

import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.*

object AppExceptionHandler {

    fun getErrorFromThrowable(throwable: Throwable): String {
        return when (throwable) {
            is ConnectException -> AppRemoteConstants.CONNECT_EXCEPTION
            is UnknownHostException -> AppRemoteConstants.UNKNOWN_HOST_EXCEPTION
            is SocketTimeoutException -> AppRemoteConstants.SOCKET_TIME_OUT_EXCEPTION
            is UnknownServiceException -> throwable.localizedMessage
            is ProtocolException -> AppRemoteConstants.PROTOCOL_EXCEPTION
            is HttpException -> {
                return when {
                    throwable.code() == 403 -> {
                        val message = throwable.message()
                        return message ?: AppRemoteConstants.FORBIDDEN_EXCEPTION
                    }
                    throwable.code() == 204 -> {
                        val message = throwable.message()
                        return message ?: AppRemoteConstants.FORBIDDEN_EXCEPTION
                    }
                    else -> try {
                        val response = throwable.response()
                        val json = JSONObject(response?.errorBody()?.string())
                        json.getString("message")
                    } catch (e1: JSONException) {
                        AppRemoteConstants.UNKNOWN_NETWORK_EXCEPTION
                    } catch (e1: IOException) {
                        AppRemoteConstants.UNKNOWN_NETWORK_EXCEPTION
                    }
                }
            }
            else -> AppRemoteConstants.UNKNOWN_NETWORK_EXCEPTION
        }
    }
}
