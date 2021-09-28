package com.example.viewbinding

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.viewbinding.databinding.FragmentTestBinding
import kotlin.random.Random

/**
 * 构造方法中如果传入了fragment布局话的，可以使用bind函数
 * 使用bind函数是在 onViewCreated{} 中使用
 *
 * viewBinding 在Fragment中的使用
 */
class Test1Fragment : Fragment(R.layout.fragment_test) {

    private var viewBinding: FragmentTestBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewBinding = FragmentTestBinding.bind(view)
        this.viewBinding = viewBinding
        viewBinding.tvTest.setOnClickListener {
            viewBinding.tvTest.text = "你好，viewBinding：${Random.nextInt(10)}"
        }
    }

    override fun onDestroyView() {
        viewBinding = null
        super.onDestroyView()
    }
}