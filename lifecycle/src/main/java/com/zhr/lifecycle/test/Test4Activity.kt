package com.zhr.lifecycle.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhr.lifecycle.R

/**
 * kotlin 语法糖
 * .let
 * .apply
 * .run
 * init{}
 */
class Test4Activity : AppCompatActivity() {

    private val test4Presenter by lazy {
        Test4Presenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
//        test4Presenter.LcObserver()
//        test4Presenter.LceObserver()
        // 只要创建了 test4Presenter对象 就可以了
        test4Presenter.addObserver()
    }
}