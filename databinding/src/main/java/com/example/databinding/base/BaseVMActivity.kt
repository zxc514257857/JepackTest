package com.example.databinding.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMActivity<T : ViewDataBinding, VM : ViewModel> : BaseViewActivity<T>() {

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 创建ViewModel
        initViewModel()
        // 观察ViewModel里的数据变化 -> 更新UI
        observerData()
        // 初始化相关控件
        initView()
        // 设置相关事件
        initEvent()
        // 开始去加载数据
        startLoadData()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(getSubVMClass())
    }

    open fun initView() {}

    open fun observerData() {}

    open fun initEvent() {}

    open fun startLoadData() {}

    abstract fun getSubVMClass(): Class<VM>
}