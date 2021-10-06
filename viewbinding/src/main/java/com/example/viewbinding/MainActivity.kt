package com.example.viewbinding

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewbinding.databinding.ActivityTestBinding

/**
 * android官方不推荐使用 kotlin-android-extensions 进行查找id操作，因此来尝试一下 viewbinding
 * 为什么不推荐呢？因为使用id是全局方式，容易引用错误导致空指针情况
 * viewBinding是轻量级的dataBinding，只绑定视图，不绑定数据，而dataBinding是双向绑定
 * build.gradle 中要配置使用viewbinding ，然后在Activity中使用ActivityxxxBinding.inflate(layoutInflater)
 *
 * viewBinding 在Activity中的使用
 *
 * https://www.bilibili.com/video/BV1mz411b7DR
 */
class MainActivity : AppCompatActivity() {

    /**
     * 使用lateinit 表示使用时再初始化，类似于by lazy
     * lateinit 修饰的var ，by lazy 修饰的val
     */
    private lateinit var binding: ActivityTestBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 这里的名字是和xml里的名字是对应的，不是和类名对应的
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.apply {
//            setContentView(this.root)
//            this.tvTest.setOnClickListener {
//                // 0 - 9
//                this.tvTest.text = "你好，viewBinding：${Random.nextInt(10)}"
//            }
//        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl, TestFragment())
//        fragmentTransaction.replace(R.id.fl, Test1Fragment())
        fragmentTransaction.commit()
    }
}