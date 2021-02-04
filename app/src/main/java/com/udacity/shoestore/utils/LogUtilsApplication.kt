package com.udacity.shoestore.utils

import android.app.Application
import timber.log.Timber

class LogUtilsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}