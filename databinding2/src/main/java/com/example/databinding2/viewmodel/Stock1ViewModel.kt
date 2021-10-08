package com.example.databinding2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.ObjectUtils

class Stock1ViewModel : ViewModel() {

    // 关心的内容：价格和数量
    // 股票价格  EditText的双向绑定
    val price by lazy {
        MutableLiveData<String>()
    }

    // 股票数量
    val num by lazy {
        MutableLiveData<String>()
    }

    // 测试数据类型转换器的写法
    val price1 by lazy {
        MutableLiveData<Double>()
    }

    val num1 by lazy {
        MutableLiveData<Double>()
    }

    // 股票总价
    val total by lazy {
        val tValue = MutableLiveData<Double>()
        tValue.value = 0.00
        tValue
    }

    // 是否同意协议  CheckBox的双向绑定
    // 双向绑定只是支持部分的View，只有View上可以键入内容，双向绑定才是有意义的。否则没有数据键入，也不会更新UI，是没有意义的
    val agreement by lazy {
        MutableLiveData<Boolean>()
    }

    fun updateTotalValue() {
        if (ObjectUtils.isEmpty(price.value)) {
            // 双向绑定了，数据为0，视图也必须为0，其实我不想让视图显示0的
//            price.value = "0"
            // 直接改结果，这样改，解决了问题
            total.value = 0.00
            return
        }
        val priceValue = price.value?.toDouble() ?: 0.00
        if (ObjectUtils.isEmpty(num.value)) {
            // 单向绑定，数据的修改，不会显示在视图上(xml上不加=号)
//            num.value = "0"
            total.value = 0.00
            return
        }
        val numValue = num.value?.toDouble() ?: 0.00
        total.value = priceValue * numValue
    }

    fun updateTotalValue1() {
        val priceValue = price1.value ?: 0.00
        val numValue = num1.value ?: 0.00
        total.value = priceValue * numValue
    }
}