package com.ulesson.domain.interactors

import com.ulesson.domain.utils.RxExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

/**
 * Created by edetebenezer on 2019-08-09
 **/

abstract class ObservableUseCaseNoParam<R>(
    private val executionThread: RxExecutionThread
) {
    private val disposable = CompositeDisposable()

    abstract fun buildUseCaseObservable(): Observable<R>

    fun executeUseCase(observer: DisposableObserver<R>) {
        disposable.add(
            buildUseCaseObservable().observeOn(executionThread.observerThread).subscribeWith(
                observer
            )
        )
    }

    fun executeUseCaseAndPerformAction(
        action: (R) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
//        disposable.add(
//            buildUseCaseObservable().doOnError {
//                errorHandler.invoke(it)
//            }.doOnNext {
//                action.invoke(it)
//            }.observeOn(executionThread.observerThread).subscribe()
//        )

        disposable.add(
            buildUseCaseObservable()
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
