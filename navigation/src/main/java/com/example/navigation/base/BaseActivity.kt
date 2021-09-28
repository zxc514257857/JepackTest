package com.example.navigation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T : ViewBinding?> : AppCompatActivity() {

    protected var viewBinding: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val superclass = javaClass.genericSuperclass
        val aClass = (superclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        try {
            val method = aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
            viewBinding = method.invoke(null, layoutInflater) as T
            setContentView(viewBinding!!.root)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}