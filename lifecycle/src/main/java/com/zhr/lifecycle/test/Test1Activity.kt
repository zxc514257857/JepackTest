package com.zhr.lifecycle.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhr.lifecycle.R

class Test1Activity:AppCompatActivity() {

    private val test1Presenter by lazy {
        Test1Presenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        test1Presenter.onCreate()
    }

    /**
     * 为了减少这种重复的 生命周期监听操作
     * google官方在 Jetpack中加入lifecycle
     */
    override fun onStart() {
        super.onStart()
        test1Presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        test1Presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        test1Presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        test1Presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        test1Presenter.onDestroy()
    }
}