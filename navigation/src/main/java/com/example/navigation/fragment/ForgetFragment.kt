package com.example.navigation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigation.base.BaseFragment
import com.example.navigation.databinding.FragmentForgetBinding

class ForgetFragment : BaseFragment<FragmentForgetBinding>() {

    companion object {
        private const val TAG: String = "ForgetFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding?.btnBack?.setOnClickListener {
            // 两种都是返回功能 ，推荐使用navigateUp() 因为这个是对popBackStack() 的封装
            findNavController().navigateUp()   // 实现左上角 <- 返回功能
//            findNavController().popBackStack()   // 实现tab栏 ◁ 返回功能
        }
        viewBinding?.tv?.text = arguments?.getString("toForget")
    }
}