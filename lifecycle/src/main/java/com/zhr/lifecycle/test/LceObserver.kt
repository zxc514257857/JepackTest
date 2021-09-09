package com.zhr.lifecycle.test

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class LceObserver : LifecycleEventObserver {

    private val TAG: String = "LceObserver"

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> Log.i(TAG, "监听GPS信号变化2")
            Lifecycle.Event.ON_STOP -> Log.i(TAG, "关闭GPS信号变化2")
            else -> {
            }
        }
    }
}