package com.gokada.domain.interactors

import com.gokada.domain.models.BaseDomainModel
import com.gokada.domain.utils.RxExecutionThread
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase <P, R> (
    private val executionThread: RxExecutionThread
) {

    private val disposable = CompositeDisposable()

    abstract fun buildSingleUseCase(param: P? = null): Single<BaseDomainModel<R>>

    fun executeSingleUseCase(param: P? = null, observer: DisposableSingleObserver<BaseDomainModel<R>>) {
        disposable.add(
            buildSingleUseCase(param).observeOn(executionThread.observerThread).subscribeWith(observer)
        )
    }

    fun executeSingleUseCaseAndPerformAction(param: P? = null, successAction: (R) -> Unit, errorAction: (String) -> Unit) {
//        disposable.add(
//            buildSingleUseCase(param).observeOn(executionThread.observerThread).doOnSuccess {
//                if (it.successful) successAction.invoke(it.data!!)
//                else errorAction.invoke(it.message!!)
//            }.subscribe()
//        )

        disposable.add(
            buildSingleUseCase(param)
                .observeOn(executionThread.observerThread)
                .subscribe({
                    successAction.invoke(it.data!!)
                }, {
                    errorAction.invoke(it.message ?: "An error occurred while performing your request, please try again.")
                })
        )

    }

    fun dispose() = disposable.dispose()
}