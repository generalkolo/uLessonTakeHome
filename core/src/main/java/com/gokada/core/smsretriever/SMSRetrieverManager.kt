package com.gokada.core.smsretriever

import android.content.Context
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.tasks.Task
import javax.inject.Inject

//TODO: Work on the SMS retriever
class SMSRetrieverManager @Inject constructor(
    private val context: Context
) {
    val task: Task<Void> = SmsRetriever.getClient(context).startSmsUserConsent("Gokada")
}