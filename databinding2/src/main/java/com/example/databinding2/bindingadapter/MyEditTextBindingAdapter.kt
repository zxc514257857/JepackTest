package com.example.databinding2.bindingadapter

import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.blankj.utilcode.util.LogUtils
import com.example.databinding2.widget.MyEditText

// 参考 ： https://developer.android.google.cn/topic/libraries/data-binding/two-way?hl=zh_cn
// 视频 ： https://www.bilibili.com/video/BV1Jy4y1p7ja?p=9
object MyEditTextBindingAdapter {

    // 数据更新通知UI更新  这里的值就是命名空间后面的值
    @BindingAdapter("numValue")
    @JvmStatic
    fun setValue(view: MyEditText, value: Int) {
        // 给View设置数据需要判断，此次修改的值和显示的值是否一样；为了避免双向绑定的死循环
        if (view.curValue != value) {
            view.curValue = value
        }
    }

    // UI更新通知数据更新   这里的值和上面的值是一样的，
    @InverseBindingAdapter(attribute = "numValue", event = "numAttrChange")
    @JvmStatic
    fun getValue(view: MyEditText): Int {
        LogUtils.i("通知ViewModel里的数据变化...")
        return view.curValue
    }


    @BindingAdapter("numAttrChange")
    @JvmStatic
    fun onNumberChange(view: MyEditText, listener: InverseBindingListener) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                LogUtils.i("触发通知ViewModel数据变化...")
                listener.onChange()
            }
        })
    }
}