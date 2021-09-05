package com.zhr.mvp2.music

import android.app.Application
import android.os.Handler

class App : Application() {

    companion object {
        @JvmStatic
        val handler = Handler()
    }

    override fun onCreate() {
        super.onCreate()
    }
}