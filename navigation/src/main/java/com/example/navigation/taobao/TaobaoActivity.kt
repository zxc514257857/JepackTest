package com.example.navigation.taobao

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navigation.R
import com.example.navigation.base.BaseActivity
import com.example.navigation.databinding.ActivityTaobaoBinding

/**
 * BottomNavigationView + Navigation
 */
class TaobaoActivity : BaseActivity<ActivityTaobaoBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        // 这里如何得到NavController，有两种方法
        // 注意：两个菜单设置对应的navigation -> fragment的id 和 menu -> item的id要是一致的，否则底边栏无法点击切换
//        // 方法一：布局为FragmentContainerView
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
//        viewBinding?.bnv?.setupWithNavController(navHostFragment.navController)
        // 方法二：布局为Fragment
        viewBinding?.bnv?.setupWithNavController(findNavController(R.id.fcv))
    }
}