package com.example.databinding2.converter

import androidx.databinding.InverseMethod
import com.blankj.utilcode.util.ObjectUtils

// 视频： https://www.bilibili.com/video/BV1Jy4y1p7ja?p=11
// 文档： https://developer.android.google.cn/topic/libraries/data-binding/two-way?hl=zh_cn
object Converter {

    // 必须要加上这个反向的方法（否则在xml不让你使用这个双向绑定的转换器）
    @InverseMethod("string2Double")
    @JvmStatic
    fun double2String(value: Double?): String {
        if (value == null) {
            return ""
        }
        return value.toString()
    }

    @JvmStatic
    fun string2Double(value: String): Double? {
        if (ObjectUtils.isEmpty(value)) {
            return null
        }
        return value.toDouble()
    }
}