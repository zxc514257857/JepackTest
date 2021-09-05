package com.zhr.mvp2.lifecycle

interface ILifeCycle {
    fun onCreate()
    fun onStart()
    fun onResume()
    fun onPause()
    fun onStop()
    fun onDestroy()
}