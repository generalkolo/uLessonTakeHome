package com.ulesson.domain.interactors

import com.ulesson.domain.utils.RxExecutionThread
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable

abstract class CompletableUseCase<in P>(
    private val executionThread: RxExecutionThread
) {
    private val disposable = CompositeDisposable()

    abstract fun buildUseCaseCompletable(param: P? = null): Completable

    fun executeCompletableUseCase(param: P?, observer: CompletableObserver) {
        disposable.add(buildUseCaseCompletable(param).observeOn(executionThread.observerThread).subscribe())
    }
    fun executeUseCaseAndPerformAction(param: P?, action: () -> Unit, errorHandler: (Throwable) -> Unit) {
//        disposable.add(
//            buildUseCaseCompletable(param).doOnError {
//                errorHandler.invoke(it)
//            }.doOnComplete {
//                action.invoke()
//            }.observeOn(executionThread.observerThread).subscribe()
//        )

        disposable.add(
            buildUseCaseCompletable(param)
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
