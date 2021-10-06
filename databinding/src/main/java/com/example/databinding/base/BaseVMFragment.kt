package com.example.databinding.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 弄成抽象的之后，自己不能实现的内容就可以再让子类去实现
 */
abstract class BaseVMFragment<T : ViewDataBinding, VM : ViewModel> : BaseViewFragment<T>() {

    protected lateinit var viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 初始化相关控件
        initView()
        // 创建ViewModel
        initViewModel()
        // 观察ViewModel里的数据变化 -> 更新UI
        observerData()
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