package com.zhr.jetpacktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * mvc架构
 * kotlin几个语法糖：
 * by lazy
 * object:  设置监听回调常用
 * companion object  方便类.调用
 * JVMStatic
 * in 遍历
 * is 包含
 * Class::class.java
 * .let{}   合并同一变量
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}