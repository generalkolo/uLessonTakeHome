package com.gokada.core.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.gokada.core.listeners.SingleArgClickListener

class SMSBroadcastReceiver constructor(
    private val smsReceivedListener: SingleArgClickListener<String>
): BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

    }
}