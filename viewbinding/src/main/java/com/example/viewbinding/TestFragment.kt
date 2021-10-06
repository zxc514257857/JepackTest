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
 * 构造方法中没有传入fragment布局的话，使用inflate函数；如果传入了的话，可以使用bind函数
 * 使用inflate函数是在 onCreateView{} 中使用
 *
 * viewBinding 在Fragment中的使用
 */
class TestFragment : Fragment() {

    private var mBinding: FragmentTestBinding? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
//        val binding = FragmentTestBinding.inflate(layoutInflater)
        // 这两种inflate方式都行，建议使用下面的方式
        val binding = FragmentTestBinding.inflate(inflater, container, false)
        this.mBinding = binding
        binding.tvTest.setOnClickListener {
            binding.tvTest.text = "你好，binding：${Random.nextInt(10)}"
        }
        return binding.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}