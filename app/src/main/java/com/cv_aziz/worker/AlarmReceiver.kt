package com.cv_aziz.worker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "🚨 Waktunya kegiatan kamu!", Toast.LENGTH_LONG).show()
    }
}
