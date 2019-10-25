package com.gokada.remote.utils

import com.google.gson.JsonParser
import retrofit2.HttpException

/**
 * Created by edetebenezer on 2019-07-27
 **/
class ApiError constructor(error: Throwable) {
    var message = "An error occurred"

    init {
        if (error is HttpException) {
            val errorJsonString = error.response()?.errorBody()?.string()
            this.message = JsonParser().parse(errorJsonString)
                /*.asJsonObject["errors"]
                .asJsonArray[0]*/
                .asJsonObject["message"]
                .asString
        } else {
            this.message = error.message ?: this.message
        }
    }
}