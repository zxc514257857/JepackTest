package com.example.databinding.activity

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.LogUtils

// 测试xml中 bindingAdapter的使用
@BindingAdapter("testcontent")
fun dealWithTaobao(textView: TextView, string: String) {
    LogUtils.e("string111:$string")
    textView.text = string
}