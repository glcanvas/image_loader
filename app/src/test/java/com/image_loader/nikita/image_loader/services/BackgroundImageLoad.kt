package com.image_loader.nikita.image_loader.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class BackgroundImageLoad : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private var isRunning = false

    override fun onCreate() {
        super.onCreate()
        isRunning = true
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return START_REDELIVER_INTENT
    }
}