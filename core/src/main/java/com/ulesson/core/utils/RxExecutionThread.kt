package com.ulesson.core.utils

import com.ulesson.domain.utils.RxExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class RxExecutionThread @Inject constructor(

): RxExecutionThread {

    override val observerThread: Scheduler
        get() = AndroidSchedulers.mainThread()
}
