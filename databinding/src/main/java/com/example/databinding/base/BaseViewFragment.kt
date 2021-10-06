package com.example.databinding.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseViewFragment<T : ViewDataBinding> : Fragment() {

    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // 让子类传入DataBinding对象以及layout布局
        val rootView = inflater.inflate(getSubLayoutId(), container, false)
        binding = DataBindingUtil.bind<T>(rootView)!!
//        binding = DataBindingUtil.inflate<T>(inflater, getSubLayoutId(), container, false)
//        val rootView = binding.root
        return rootView
    }

    // 再谈一次abstract和open的区别，abstract是子类必须实现，不用方法体；open是子类可选实现，要有方法体
    abstract fun getSubLayoutId(): Int
}