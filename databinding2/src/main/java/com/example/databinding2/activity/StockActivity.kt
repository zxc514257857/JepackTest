package com.example.databinding2.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ObjectUtils
import com.example.databinding2.R
import kotlinx.android.synthetic.main.activity_stock.*

/**
 * stock ： 股票
 * 使用普通方式实现
 */
class StockActivity : AppCompatActivity() {

    private var price = 0.00
    private var num = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)
        initEvent()
    }

    private fun initEvent() {
        etCurPriceCentent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                LogUtils.e("content1:$p0")
                val value = p0.toString()
                // 处理字段保留两位小数要求
                // 如果字符串包含小数点
                if (value.contains(".")) {
                    // 那么就要找到小数点对应的索引
                    val pointIndex = value.indexOf(".")
                    // 搞明白这个pointIndex 对应的意思 (从左往右数，自己在左下角标的位置)
                    // 如果要保证小数点后最多两位小数，就要保证总长度-pointIndex > 3
                    LogUtils.e("pointIndex:$pointIndex")
                    if (value.length - pointIndex > 3) {
                        // 去掉后面多余的内容
                        p0?.delete(pointIndex + 2, value.length - 1)
                    }
                }
                // 处理输入后预估总价变动的需求
                if (ObjectUtils.isEmpty(value)) {
                    price = 0.00
                    getTotalPrice()
                    return
                }
                price = value.toDouble()
                getTotalPrice()
            }
        })

        etCurNumCentent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                LogUtils.e("content2:$p0")
                // 处理输入后预估总价变动的需求
                val value = p0.toString()
                if (ObjectUtils.isEmpty(value)) {
                    num = 0.00
                    getTotalPrice()
                    return
                }
                num = value.toDouble()
                getTotalPrice()
            }
        })
    }

    fun getTotalPrice() {
        if (price < 0.00) return
        if (num < 0.00) return
        val total = price * num
        tvCurTotalPriceContent.text = String.format("%.2f", total)
    }
}