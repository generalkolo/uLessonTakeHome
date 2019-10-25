package com.gokada.repository.models

/**
 * Created by Isaac Iniongun on 2019-10-10
 * For gokada-app-android project
 */

data class BaseRepositoryDataWrapper<T>(val error: Boolean?, val code: Int?, val message: String?, val response: T? = null)