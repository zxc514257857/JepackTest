package com.zhr.mvp2.lifecycle

/**
 * 管理注册进来的接口 ILifeCycle  保存当前View的生命周期状态
 */
class LifeCycleProvider {

    private val lifeCycleListener = mutableListOf<ILifeCycle>()
    private var currentLifeState: LifeState? = null

    fun addLifeListener(lifeCycle: ILifeCycle) {
        if (!lifeCycleListener.contains(lifeCycle)) {
            lifeCycleListener.add(lifeCycle)
        }
    }

    private fun removeListener(lifeCycle: ILifeCycle) {
        lifeCycleListener.remove(lifeCycle)
    }

    fun makeLifeState(state: LifeState?) {
        currentLifeState = state
        when (state) {
            LifeState.CREATE -> {
                lifeCycleListener.forEach {
                    it.onCreate()
                }
            }
            LifeState.START -> {
                lifeCycleListener.forEach {
                    it.onStart()
                }
            }
            LifeState.RESUME -> {
                lifeCycleListener.forEach {
                    it.onResume()
                }
            }
            LifeState.PAUSE -> {
                lifeCycleListener.forEach {
                    it.onPause()
                }
            }
            LifeState.STOP -> {
                lifeCycleListener.forEach {
                    it.onStop()
                }
            }
            LifeState.DESTROY -> {
                lifeCycleListener.forEach {
                    it.onDestroy()
                }
                // 在onDestroy中清空此Activity中的所有生命周期数据
                lifeCycleListener.clear()
            }
        }
    }
}