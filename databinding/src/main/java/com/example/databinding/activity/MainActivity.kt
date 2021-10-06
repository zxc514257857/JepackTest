package com.example.databinding.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databinding.R
import com.example.databinding.databinding.ActivityMainBinding
import com.example.databinding.domain.Gender
import com.example.databinding.domain.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 这里的Binding名称就是布局名称，这个名称是可以修改的，在data标签这里添加class字段进行修改
        // dataBinding其实就是对viewBinding的一个封装，底层用的还是viewBinding
        // 绑定方式一：
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(dataBinding.root)
        // 绑定方式二：使用DataBindingUtil  inflate方式和bind方式适合在适配器中使用
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        // dataBinding和viewBinding 的用法一样，也可以设置文字数据
        binding.user = User("张三丰", 22, Gender.Male)

        // 如何不通过构造方法传参，这样写有点问题，跑不起来
//        binding.user?.run {
//            name = "xxx"
//            age = 18
//            gender = Gender.Female
//        }
    }
}