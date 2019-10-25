package com.gokada.core.utils

import com.gokada.domain.utils.RxExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class GokadaRxExecutionThread @Inject constructor(

): RxExecutionThread {

    override val observerThread: Scheduler
        get() = AndroidSchedulers.mainThread()
}