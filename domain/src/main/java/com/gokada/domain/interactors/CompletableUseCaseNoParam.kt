package com.gokada.domain.interactors

import com.gokada.domain.utils.RxExecutionThread
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by edetebenezer on 2019-09-16
 **/
abstract class CompletableUseCaseNoParam(
    private val executionThread: RxExecutionThread
) {
    private val disposable = CompositeDisposable()

    abstract fun buildUseCaseCompletable(): Completable

    fun executeCompletableUseCase(observer: CompletableObserver) {
        disposable.add(buildUseCaseCompletable().observeOn(executionThread.observerThread).subscribe())
    }

    fun executeUseCaseAndPerformAction(action: () -> Unit, errorHandler: (Throwable) -> Unit) {
//        disposable.add(
//            buildUseCaseCompletable(param).doOnError {
//                errorHandler.invoke(it)
//            }.doOnComplete {
//                action.invoke()
//            }.observeOn(executionThread.observerThread).subscribe()
//        )

        disposable.add(
            buildUseCaseCompletable()
                .observeOn(executionThread.observerThread)
                .subscribe({
                    action.invoke()
                }, {
                    errorHandler.invoke(it)
                })
        )
    }

    fun dispose() = disposable.dispose()
}