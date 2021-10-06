package com.example.databinding1.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
import com.example.databinding1.R
import com.example.databinding1.databinding.ActivityTemperatureBinding
import com.example.databinding1.domain.TempSensorType
import com.example.databinding1.viewmodel.TemperatureViewModel

/**
 * 体温、环境温度、腕温
 * include、ViewStub
 * include 加载界面是所有都加载进来，通过显示隐藏去处理布局
 * VieStub 加载界面是按需加载，判断为没有载入的布局才进行加载，并且是调用载入方法才会进行加载
 */
class TemperatureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 获取databinding
        val binding = DataBindingUtil.setContentView<ActivityTemperatureBinding>(this,
            R.layout.activity_temperature)
        // 获取viewModel
        val viewModel = ViewModelProvider(this).get(TemperatureViewModel::class.java)

        // 观察viewModel中的数据变化
        viewModel.run {
            // 对设备支持类型进行监控
            supportType.observe(this@TemperatureActivity, Observer {
                when (it) {
                    TempSensorType.BODY_TEMP -> {
                        // 如果没有载入 则载入
                        binding.bodyTemp.apply {
                            if(!isInflated){
                                viewStub?.inflate()
                            }
                        }
                    }
                    TempSensorType.BODY_ENV_TEMP -> {
                        binding.bodyEnvTemp.apply {
                            if(!isInflated){
                                viewStub?.inflate()
                            }
                        }
                    }
                    TempSensorType.NONE -> {
                        binding.notSupportTemp.apply {
                            if(!isInflated){
                                viewStub?.inflate()
                            }
                        }
                    }
                    else -> {
                        binding.notSupportTemp.apply {
                            if(!isInflated){
                                viewStub?.inflate()
                            }
                        }
                    }
                }
            })
        }
    }
}