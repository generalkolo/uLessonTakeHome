package com.gokada.local.impl.auth

import com.gokada.local.models.auth.OnBoardingDoneLocalModel
import com.gokada.local.preference.IGokadaPreference
import com.gokada.local.room.dao.OnBoardingStatusDao
import com.gokada.repository.local.auth.IAuthenticationLocal
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.concurrent.Executor
import javax.inject.Inject

class GokadaAuthenticationLocalImpl @Inject constructor(
    private val onBoardingStatusDao: OnBoardingStatusDao,
    private val preference: IGokadaPreference,
    private val executor: Executor
) : IAuthenticationLocal {
    override fun isUserFullyRegistered(): Observable<Boolean> =
        Observable.just(preference.isUserFullyRegistered())

    override fun saveOnBoardingStatus(status: Boolean): Completable {
        return Completable.defer {
            executor.execute{
                onBoardingStatusDao.saveOnBoardingStatus(OnBoardingDoneLocalModel(status))
            }
            Completable.complete()
        }
    }

    override fun hasUserBeenFullyOnBoarded(): Observable<Boolean> =
        onBoardingStatusDao.getOnBoardingStatus().map {
            it.isCompleted
        }
}