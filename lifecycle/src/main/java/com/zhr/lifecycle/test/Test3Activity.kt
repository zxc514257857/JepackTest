package com.zhr.lifecycle.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhr.lifecycle.R

class Test3Activity : AppCompatActivity() {

    private val lcObserver by lazy {
        LcObserver()
    }

    private val lceObserver by lazy {
        LceObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        // LifecycleObserver 和 LifecycleEventObserver
//        lifecycle.addObserver(lcObserver)
//        lifecycle.addObserver(lceObserver)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val testFragment = TestFragment()
        fragmentTransaction.replace(R.id.framelayout, testFragment)
        fragmentTransaction.commit()
    }
}