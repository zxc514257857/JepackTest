package com.example.databinding1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databinding1.domain.TempSensorType

/**
 * 要学习的点就是，怎么把ViewStub外面的数据传到这个布局里面去，就是用命名空间:里面变量名=@｛外面的变量名｝ 这样传递的
 */
class TemperatureViewModel : ViewModel() {

    // 关心 表盘类型
    val supportType by lazy {
        val type = MutableLiveData<TempSensorType>()
        // 设置默认支持的表盘类型
        type.postValue(TempSensorType.BODY_ENV_TEMP)
        type
    }

    // 关心 体温以及环境温度数值
    val bodyTemp by lazy {
        val bodyT = MutableLiveData<Float>()
        bodyT.postValue(36.8f)
        bodyT
    }

    val envTemp by lazy {
        val envT = MutableLiveData<Float>()
        envT.postValue(25.5f)
        envT
    }
}