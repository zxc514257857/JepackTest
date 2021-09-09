package com.zhr.lifecycle.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.zhr.lifecycle.R

//class Test2Activity : AppCompatActivity(), LifecycleObserver {
class Test2Activity : AppCompatActivity(), LifecycleEventObserver {

    private val TAG: String = "Test2Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        lifecycle.addObserver(this)
    }

    /**
     * 如果实现LifecycleObserver 则是主动监听生命周期变化
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun openGps() {
        Log.i(TAG, "监听GPS信号变化1")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun closeGps() {
        Log.i(TAG, "关闭GPS信号变化1")
    }

    /**
     * 如果实现的是LifecycleEventObserver 则是被动监听生命周期变化
     */
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> Log.i(TAG, "监听GPS信号变化2")
            Lifecycle.Event.ON_STOP -> Log.i(TAG, "关闭GPS信号变化2")
            else -> {
            }
        }
    }
}