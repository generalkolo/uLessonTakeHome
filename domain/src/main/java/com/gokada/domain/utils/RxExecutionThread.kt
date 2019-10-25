package com.gokada.domain.utils

import io.reactivex.Scheduler

interface  RxExecutionThread {
    val observerThread: Scheduler
}