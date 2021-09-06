package com.zhr.mvp2.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhr.mvp2.lifecycle.LifeCycleProvider
import com.zhr.mvp2.lifecycle.LifeState

/**
 * 实现功能：通过BaseActivity将生命周期方法实现抽取出来
 * 让其他Activity也可以通知Presenter生命周期状态变化
 */
open class BaseActivity : AppCompatActivity() {

    /********************把这部分抽取出来到LifeCycleProvider，Activity、Fragment等谁用谁来取*******************/
    // 为什么要创建一个生命周期的集合呢？ 因为一个Activity中要添加的生命周期数量可能不止一个！
//    private val lifeCycleListener = mutableListOf<ILifeCycle>()
//
//    fun addLifeListener(lifeCycle: ILifeCycle) {
//        if (!lifeCycleListener.contains(lifeCycle)) {
//            lifeCycleListener.add(lifeCycle)
//        }
//    }
//
//    private fun removeListener(lifeCycle: ILifeCycle) {
//        lifeCycleListener.remove(lifeCycle)
//    }

    /********************把这部分抽取出来，Activity、Fragment等谁用谁来取*******************/

    val lifeCycleProvider by lazy {
        LifeCycleProvider()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 如果在onCreate方法中，就把这个生命周期中注册的所有内容都调用起来
//        lifeCycleListener.forEach {
//            it.onCreate()
//        }
        lifeCycleProvider.makeLifeState(LifeState.CREATE)
    }

    /**
     * 把生命周期方法抽取到基类中
     */
    override fun onStart() {
        super.onStart()
//        lifeCycleListener.forEach {
//            it.onStart()
//        }
        lifeCycleProvider.makeLifeState(LifeState.START)
    }

    override fun onResume() {
        super.onResume()
//        lifeCycleListener.forEach {
//            it.onResume()
//        }
        lifeCycleProvider.makeLifeState(LifeState.RESUME)
    }

    override fun onPause() {
        super.onPause()
//        lifeCycleListener.forEach {
//            it.onPause()
//        }
        lifeCycleProvider.makeLifeState(LifeState.PAUSE)
    }

    override fun onStop() {
        super.onStop()
//        lifeCycleListener.forEach {
//            it.onStop()
//        }
        lifeCycleProvider.makeLifeState(LifeState.STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
//        lifeCycleListener.forEach {
//            it.onDestroy()
//        }
        lifeCycleProvider.makeLifeState(LifeState.DESTROY)
    }
}