package com.zhr.lifecycle.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.zhr.lifecycle.R

class TestFragment : Fragment(), LifecycleObserver {

    private val lcObserver by lazy {
        LcObserver()
    }

    private val lceObserver by lazy {
        LceObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        lifecycle.addObserver(lceObserver)
//        lifecycle.addObserver(lcObserver)
        return inflater.inflate(R.layout.activity_music, container, false)
    }
}