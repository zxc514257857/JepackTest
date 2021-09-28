package com.example.navigation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.navigation.R

/**
 * 代替LoginFragment进行测试用的
 */
class AFragment : Fragment() {

    companion object {
        private const val TAG: String = "AFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_a, container, false)
        rootView.findViewById<TextView>(R.id.btnToForget)?.setOnClickListener {
            // 这里传入的是actionId，通过action找到要跳转的页面
            // 这个页面有几个要跳转出的，就要配置几个action
            findNavController().navigate(R.id.to_fg_forget)
            Log.i(TAG, "onCreateView1: ")
        }
        rootView.findViewById<TextView>(R.id.btnToRegister)?.setOnClickListener {
            // 通过代码配置fragment跳转动画
            val navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.nav_default_enter_anim)
                .setExitAnim(R.anim.nav_default_exit_anim)
                .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
                .build()
            findNavController().navigate(R.id.to_fg_register, null, navOptions)
            Log.i(TAG, "onCreateView2 ")
        }
        rootView.findViewById<TextView>(R.id.btnToAgreement)?.setOnClickListener {
            findNavController().navigate(R.id.to_av_agreement)
            Log.i(TAG, "onCreateView3: ")
        }
        return rootView
    }
}