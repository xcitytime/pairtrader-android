package com.example.pairtrader.alerts

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class AlertWorker(appContext: Context, params: WorkerParameters) : Worker(appContext, params) {
    override fun doWork(): Result {
        Notify.send(applicationContext, "PairTrader Alert", "Divergence detected (demo)")
        return Result.success()
    }
}
