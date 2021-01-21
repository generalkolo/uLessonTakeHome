package com.ulesson.domain.utils

import io.reactivex.Scheduler

interface  RxExecutionThread {
    val observerThread: Scheduler
}
