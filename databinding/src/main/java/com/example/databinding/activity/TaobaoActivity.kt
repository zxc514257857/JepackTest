package com.example.databinding.activity

import com.example.databinding.base.BaseVMActivity
import com.example.databinding.databinding.ActivityTaobaoBinding
import com.example.databinding.viewmodel.TaobaoViewModel

/**
 * androidStudio 中返回上一步操作的快捷键是
 * ctrl + alt + left或者right
 */
class TaobaoActivity : BaseVMActivity<ActivityTaobaoBinding, TaobaoViewModel>() {

    override fun getSubVMClass(): Class<TaobaoViewModel> = TaobaoViewModel::class.java

    override fun initView() {
        super.initView()
    }

    override fun observerData() {
        super.observerData()
    }
}