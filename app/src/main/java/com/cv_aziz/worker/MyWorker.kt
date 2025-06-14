package com.cv_aziz.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.d("MyWorker", "🔔 WorkManager dijalankan!")
        return Result.success()
    }
}
