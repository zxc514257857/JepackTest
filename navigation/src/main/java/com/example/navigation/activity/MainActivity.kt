package com.example.navigation.activity

import android.os.Bundle
import android.util.Log
import com.example.navigation.base.BaseActivity
import com.example.navigation.databinding.ActivityMainBinding

/**
 * 实现功能：Activity中有三个Fragment，Fragment之间进行相互跳转，Fragment与Activity的跳转，切换的动画配置
 * Fragment动画切换在action中配置，Activity动画切换在themes主题中配置
 * 同时使用viewBinding功能，BaseActivity和BaseFragment中使用viewBinding
 *
 * https://www.bilibili.com/video/BV1Ui4y1j7qi
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        private const val TAG: String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding?.fragmentContainerView?.setOnClickListener {
            Log.i(TAG, "onCreate1: ")
        }
    }
}