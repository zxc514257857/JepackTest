package com.example.databinding2.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText

/**
 * 自定义控件如何进行双向绑定
 */
class MyEditText : EditText {

    var curValue: Int = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr)
}