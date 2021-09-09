
package com.zhr.lifecycle.test

import android.util.Log
import androidx.lifecycle.*

class Test4Presenter(owner: LifecycleOwner) {

    private val TAG: String = "Test4Presenter"

    private val lcObserver by lazy {
        LcObserver()
    }

    private val lceObserver by lazy {
        LceObserver()
    }

    fun addObserver(){}

    init {
        owner.lifecycle.addObserver(lcObserver)
//        owner.lifecycle.addObserver(lceObserver)
    }

    inner class LcObserver : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun openGps() {
            Log.i(TAG, "监听GPS信号变化1")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun closeGps() {
            Log.i(TAG, "关闭GPS信号变化1")
        }
    }

    inner class LceObserver : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_START -> Log.i(TAG, "监听GPS信号变化2")
                Lifecycle.Event.ON_STOP -> Log.i(TAG, "关闭GPS信号变化2")
                else -> {
                }
            }
        }
    }
}