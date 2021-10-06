package com.example.navigation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<T : ViewBinding?> : Fragment() {

    companion object {
        private const val TAG = "BaseFragment"
    }

    protected var binding: T? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        try {
            val superclass = javaClass.genericSuperclass
            //获得父类的泛型参数的实际类型
            val aClass = (superclass as ParameterizedType).actualTypeArguments[0] as Class<*>
            //获取inflate方法 传入相应的参数
            val method = aClass.getDeclaredMethod("inflate",
                LayoutInflater::class.java,
                ViewGroup::class.java,
                Boolean::class.javaPrimitiveType)
            binding = method.invoke(null, layoutInflater, container, false) as T
        } catch (e: Exception) {
            e.printStackTrace()
        }
//        testData()
        return binding!!.root
    }

    /**
     * open和abstract的区别：
     * open是不强制重写，不重写就用父类的。有方法体 ;
     * open是打开让子类重写，子类如果不调用super.方法名()，就完全覆盖了父类，如果调用了就在父类基础上添加内容
     *
     * abstract是强制实现。没有方法体
     */
//    abstract fun getLayoutResId(): Int
//
//    open fun testData() {
//        Log.i(TAG, "testData1: ")
//    }
}