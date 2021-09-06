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

    // 需要通知 lifeCycle的变化


    fun makeLifeState(state: LifeState?) {
        currentLifeState = state
        when (state) {

        }
    }
}