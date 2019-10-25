package com.gokada.remote.interceptor.responseinterceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

class AuthenticationResponseInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val body = response.body()!!.string()
        val responseBody = try {
            JSONObject(body)
        } catch (e: Exception) {
            JSONArray(body)
        }

        var newResponseBody: Any? = null

        return when (responseBody) {
            is JSONObject -> {
                newResponseBody = when {
                    responseBody.has("errors") -> {
                        /**
                         * An error from the server
                         */
                        val errorArray = responseBody.getJSONArray("errors")
                        val errorMessage = JSONObject(errorArray[0].toString()).getString("message")
                        JSONObject().apply {
                            put("message", errorMessage)
                            put("success", false)
                            put("error", true)
                        }
                    }
                    response.code() == 204 -> JSONObject().apply {
                        put("data", "Successful action")
                        put("success", true)
                        put("error", false)
                    }
                    else -> JSONObject().apply {
                        put("data", responseBody)
                        put("success", true)
                        put("error", false)
                    }
                }
                response.newBuilder().body(
                    ResponseBody.create(response.body()!!.contentType(), newResponseBody.toString())
                ).build()
            }
            is JSONArray -> {
                val mainObject = JSONObject(responseBody[0].toString())
                newResponseBody = when {
                    mainObject.has("errors") -> {
                        /**
                         * An error from the server
                         */
                        val errorArray = mainObject.getJSONArray("errors")
                        val errorMessage = JSONObject(errorArray[0].toString()).getString("message")
                        JSONObject().apply {
                            put("message", errorMessage)
                            put("success", false)
                            put("error", true)
                        }
                    }
                    else -> JSONObject().apply {
                        put("data", responseBody)
                        put("success", true)
                        put("error", false)
                    }
                }
                response.newBuilder().body(
                    ResponseBody.create(response.body()!!.contentType(), newResponseBody.toString())
                ).build()
            }
            else -> {
                response.newBuilder().body(
                    ResponseBody.create(response.body()!!.contentType(), newResponseBody.toString())
                ).build()
            }
        }
    }
}