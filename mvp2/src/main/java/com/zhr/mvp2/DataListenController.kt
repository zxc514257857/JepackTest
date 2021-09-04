package com.zhr.mvp2

class DataListenController<T> {

    private val blocks = arrayListOf<(T?) -> Unit>()
    var value: T? = null
        // 当数据变化的时候，就通知更新
        set(value: T?) {
            blocks.forEach {
                it.invoke(value)
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