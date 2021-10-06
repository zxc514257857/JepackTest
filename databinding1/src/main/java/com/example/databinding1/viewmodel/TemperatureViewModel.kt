package com.example.databinding1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databinding1.domain.TempSensorType

class TemperatureViewModel : ViewModel() {

    // 关心 表盘类型
    val supportType by lazy {
        val type = MutableLiveData<TempSensorType>()
        // 设置默认支持的表盘类型为空
        type.postValue(TempSensorType.BODY_ENV_TEMP)
        type
    }

    // 关心 体温以及环境温度数值
    val bodyTemp = MutableLiveData<Float>()
    val envTemp = MutableLiveData<Float>()

}