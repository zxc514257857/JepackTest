package com.zhr.lifecycle.test

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LcObserver : LifecycleObserver {

    private val TAG: String = "LcObserver"

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun openGps(){
        Log.i(TAG, "监听GPS信号变化1")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun closeGps(){
        Log.i(TAG, "关闭GPS信号变化1")
    }
}