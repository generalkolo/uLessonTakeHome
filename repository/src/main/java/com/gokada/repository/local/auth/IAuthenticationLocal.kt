package com.gokada.repository.local.auth

import io.reactivex.Completable
import io.reactivex.Observable

interface IAuthenticationLocal {
    fun isUserFullyRegistered(): Observable<Boolean>

    fun saveOnBoardingStatus(status: Boolean): Completable

    fun hasUserBeenFullyOnBoarded(): Observable<Boolean>
}