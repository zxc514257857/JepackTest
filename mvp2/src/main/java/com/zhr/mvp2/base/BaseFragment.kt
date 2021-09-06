package com.zhr.mvp2.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zhr.mvp2.lifecycle.LifeCycleProvider
import com.zhr.mvp2.lifecycle.LifeState

open class BaseFragment : Fragment() {

    val lifeCycleProvider by lazy {
        LifeCycleProvider()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifeCycleProvider.makeLifeState(LifeState.CREATE)
    }

    override fun onStart() {
        super.onStart()
        lifeCycleProvider.makeLifeState(LifeState.START)
    }

    override fun onResume() {
        super.onResume()
        lifeCycleProvider.makeLifeState(LifeState.RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifeCycleProvider.makeLifeState(LifeState.PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifeCycleProvider.makeLifeState(LifeState.STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifeCycleProvider.makeLifeState(LifeState.DESTROY)
    }
}