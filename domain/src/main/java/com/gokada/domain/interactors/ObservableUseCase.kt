package com.gokada.domain.interactors

import com.gokada.domain.utils.RxExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<in P, R>(
    private val executionThread: RxExecutionThread
) {
    private val disposable = CompositeDisposable()

    abstract fun buildUseCaseObservable(param: P?): Observable<R>

    fun executeUseCase(param: P?, observer: DisposableObserver<R>) {
        disposable.add(
            buildUseCaseObservable(param).observeOn(executionThread.observerThread).subscribeWith(observer)
        )
    }

    fun executeUseCaseAndPerformAction(param: P? = null, action: (R) -> Unit, errorHandler: (Throwable) -> Unit) {
        /*disposable.add(
            buildUseCaseObservable(param).doOnError {
                errorHandler.invoke(it)
            }.doOnNext {
                action.invoke(it)
            }.observeOn(executionThread.observerThread).subscribe()
        )*/

        disposable.add(
            buildUseCaseObservable(param)
                .observeOn(executionThread.observerThread)
                .subscribe({
                    action.invoke(it)
                }, {
                    errorHandler.invoke(it)
                })
        )
    }

    fun dispose() = disposable.dispose()
}