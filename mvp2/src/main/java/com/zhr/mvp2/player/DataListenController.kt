package com.zhr.mvp2.player

import android.os.Looper
import com.zhr.mvp2.music.App

class DataListenController<T> {

    private val blocks = arrayListOf<(T?) -> Unit>()
    var value: T? = null
        // 当数据变化的时候，就通知更新
        set(value: T?) {
            // 确保数据更新是通过主线程进行分发
            // 如果当前线程为主线程
            if(Looper.getMainLooper().thread == Thread.currentThread()){
                blocks.forEach {
                    it.invoke(value)
                }
            }else {
                // 如果不为主线程则进行线程切换
                App.handler.post{
                    blocks.forEach {
                        it.invoke(value)
                    }
                }
            }
//            this.value = value
            field = value
        }

    // block函数当初参数（函数名称叫block，传入的参数T，无返回值）
    fun addListener(block: (T?) -> Unit) {
        if (!blocks.contains(block)) {
            blocks.add(block)
        }
    }
}