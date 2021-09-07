package com.zhr.mvp2.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhr.mvp2.lifecycle.ILifecycleOwner
import com.zhr.mvp2.lifecycle.LifeState
import com.zhr.mvp2.lifecycle.LifecycleProvider

/**
 * 实现功能：通过BaseActivity将生命周期方法实现抽取出来
 * 让其他Activity也可以通知Presenter生命周期状态变化
 */
open class BaseActivity : AppCompatActivity(), ILifecycleOwner {

    /********************把这部分抽取出来到LifecycleProvider，Activity、Fragment等谁用谁来取*******************/
    // 为什么要创建一个生命周期的集合呢？ 因为一个Activity中要添加的生命周期数量可能不止一个！
//    private val lifecycleListener = mutableListOf<ILifecycle>()
//
//    fun addLifeListener(lifecycle: ILifecycle) {
//        if (!lifecycleListener.contains(lifecycle)) {
//            lifecycleListener.add(lifecycle)
//        }
//    }
//
//    private fun removeListener(lifecycle: ILifecycle) {
//        lifecycleListener.remove(lifecycle)
//    }

    /********************把这部分抽取出来，Activity、Fragment等谁用谁来取*******************/

    val lifeProvider by lazy {
        LifecycleProvider()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 如果在onCreate方法中，就把这个生命周期中注册的所有内容都调用起来
//        lifecycleListener.forEach {
//            it.onCreate()
//        }
        lifeProvider.makeLifeState(LifeState.CREATE)
    }

    /**
     * 把生命周期方法抽取到基类中
     */
    override fun onStart() {
        super.onStart()
//        lifecycleListener.forEach {
//            it.onStart()
//        }
        lifeProvider.makeLifeState(LifeState.START)
    }

    override fun onResume() {
        super.onResume()
//        lifecycleListener.forEach {
//            it.onResume()
//        }
        lifeProvider.makeLifeState(LifeState.RESUME)
    }

    override fun onPause() {
        super.onPause()
//        lifecycleListener.forEach {
//            it.onPause()
//        }
        lifeProvider.makeLifeState(LifeState.PAUSE)
    }

    override fun onStop() {
        super.onStop()
//        lifecycleListener.forEach {
//            it.onStop()
//        }
        lifeProvider.makeLifeState(LifeState.STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
//        lifecycleListener.forEach {
//            it.onDestroy()
//        }
        lifeProvider.makeLifeState(LifeState.DESTROY)
    }

    override fun getLifecycleProvider(): LifecycleProvider {
        return lifeProvider
    }
}