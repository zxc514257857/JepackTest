package com.zhr.lifecycle.test

import android.util.Log

class Test1Presenter {

    private val TAG: String = "Test1Presenter"

    fun onCreate() {}

    fun onStart() {
        Log.i(TAG, "监听GPS信号变化")
    }

    fun onResume() {}

    fun onPause() {}

    fun onStop() {
        Log.i(TAG, "关闭GPS信号变化")
    }

    fun onDestroy() {}
}