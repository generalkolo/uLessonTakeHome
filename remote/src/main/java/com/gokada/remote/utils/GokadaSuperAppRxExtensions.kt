package com.gokada.remote.utils

import com.gokada.remote.base.BaseNetworkModel
import io.reactivex.Single

fun <T> Single<BaseNetworkModel<T>>.performActionOnError(): Single<BaseNetworkModel<T>> {
    return onErrorReturn {
        BaseNetworkModel(
            success = false,
            message = GokadaSuperAppExceptionHandler.getErrorFromThrowable(it)
        )
    }
}