package com.example.navigation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.navigation.R
import com.example.navigation.base.BaseFragment
import com.example.navigation.databinding.FragmentLoginBinding

/**
 * 根据泛型判断是哪个页面的布局
 */
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    companion object {
        private const val TAG: String = "LoginFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 测试返回操作
        viewBinding?.btnToForget?.setOnClickListener {
            // 这里传入的是actionId，通过action找到要跳转的页面
            // 这个页面有几个要跳转出的，就要配置几个action
            val bundle = Bundle()
            // 设置Bundle，在另一个页面的Arguement中获取Bundle(Fragment Bundle传参)
            bundle.putString("toForget", "00000")
            findNavController().navigate(R.id.to_fg_forget, bundle, null)
        }

        // 测试动画操作
        // 测试三连跳及弹栈操作 login -> register -> avatar -> login
        viewBinding?.btnToRegister?.setOnClickListener {
            // 通过代码配置fragment跳转动画 (设置不生效，不知道什么原因)
            val navOptions = NavOptions.Builder()
                .setEnterAnim(R.anim.nav_default_enter_anim)
                .setExitAnim(R.anim.nav_default_exit_anim)
                .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
                .build()
            val bundle = Bundle()
            // 设置Bundle，在另一个页面的Arguement中获取Bundle(Fragment Bundle传参)
            bundle.putString("toRegister", "11111")
            findNavController().navigate(R.id.to_fg_register, bundle, navOptions)
        }

        // 测试Activity跳转操作
        // 测试Activity的 “元素共享” 功能
        // 1.对需要共享的view分别设置transitionName
        // 2.navigate()方法里面传入extras参数
        // 3.在另一边Activity修改背景颜色（不用像Fragment一样设置动画效果）
        viewBinding?.btnToAgreement?.setOnClickListener {
            val imagePair = Pair<View, String>(viewBinding?.iv1!!, "ivShared")
            val textPair = Pair<View, String>(viewBinding?.tv1!!, "tvShared")
            val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(),
                    imagePair,
                    textPair)
            // 将options 转换为Pire
            val extras = ActivityNavigator.Extras.Builder().setActivityOptions(options).build()
            val bundle = Bundle()
            // 设置Bundle，在另一个页面的Arguement中获取Bundle
            bundle.putString("toAgreement", "22222")
            findNavController().navigate(R.id.to_av_agreement, bundle, null, extras)
        }

        // 测试 Fragment的 “元素共享” 功能（不用单独配置跳转动画，会影响原显示动画展示）
        // 1.对需要共享的view分别设置transitionName
        // 2.navigate()方法里面传入extras参数
        // 3.在另一边Fragment中设置Animation
        viewBinding?.btnToSetting?.setOnClickListener {
            // 传入的参数是：本页面iv和 transitionName（需要共享的view的transitionName 需要是一样的）
//            val imagePair = Pair<View, String>(viewBinding?.iv1!!, "ivShared")
//            val textPair = Pair<View, String>(viewBinding?.tv1!!, "tvShared")
//            // 一个参数的元素共享
////            val extras = FragmentNavigatorExtras(imagePair)
//            // 多个参数的元素共享
//            val extras = FragmentNavigatorExtras(imagePair, textPair)
            val extras = FragmentNavigatorExtras(viewBinding?.iv1!! to "ivShared",
                viewBinding?.tv1!! to "tvShared")
            val bundle = Bundle()
            // 设置Bundle，在另一个页面的Arguement中获取Bundle(Fragment Bundle传参)
            bundle.putString("toSetting", "33333")
            findNavController().navigate(R.id.to_fg_setting, bundle, null, extras)
        }
    }

//    override fun testData() {
//        // 不加super是去掉父类内容，只用子类内容
//        super.testData()
//        Log.i(TAG, "testData2: ")
//    }
}