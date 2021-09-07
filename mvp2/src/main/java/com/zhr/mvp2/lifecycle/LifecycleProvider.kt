package com.zhr.mvp2.lifecycle

/**
 * 管理注册进来的接口 ILifecycle  保存当前View的生命周期状态
 */
class LifecycleProvider {

    private val lifecycleListener = mutableListOf<ILifecycle>()
    private var currentLifeState: LifeState? = null

    fun addLifeListener(lifecycle: ILifecycle) {
        if (!lifecycleListener.contains(lifecycle)) {
            lifecycleListener.add(lifecycle)
        }
    }

    private fun removeListener(lifecycle: ILifecycle) {
        lifecycleListener.remove(lifecycle)
    }

    fun makeLifeState(state: LifeState?) {
        currentLifeState = state
        when (state) {
            LifeState.CREATE -> {
                lifecycleListener.forEach {
                    it.onCreate()
                }
            }
            LifeState.START -> {
                lifecycleListener.forEach {
                    it.onStart()
                }
            }
            LifeState.RESUME -> {
                lifecycleListener.forEach {
                    it.onResume()
                }
            }
            LifeState.PAUSE -> {
                lifecycleListener.forEach {
                    it.onPause()
                }
            }
            LifeState.STOP -> {
                lifecycleListener.forEach {
                    it.onStop()
                }
            }
            LifeState.DESTROY -> {
                lifecycleListener.forEach {
                    it.onDestroy()
                }
                // 在onDestroy中清空此Activity中的所有生命周期数据
                lifecycleListener.clear()
            }
        }
    }
}