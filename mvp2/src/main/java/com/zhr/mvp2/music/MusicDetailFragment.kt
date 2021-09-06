package com.zhr.mvp2.music

import com.zhr.mvp2.base.BaseFragment

class MusicDetailFragment : BaseFragment() {

//    private val lifeCycleProvider by lazy {
//        LifeCycleProvider()
//    }

    private val musicPresenter by lazy {
        MusicPresenter()
    }

    init {
        lifeCycleProvider.addLifeListener(musicPresenter)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        lifeCycleProvider.makeLifeState(LifeState.CREATE)
//    }
//
//    override fun onStart() {
//        super.onStart()
//        lifeCycleProvider.makeLifeState(LifeState.START)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        lifeCycleProvider.makeLifeState(LifeState.RESUME)
//    }
//
//    override fun onPause() {
//        super.onPause()
//        lifeCycleProvider.makeLifeState(LifeState.PAUSE)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        lifeCycleProvider.makeLifeState(LifeState.STOP)
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        lifeCycleProvider.makeLifeState(LifeState.DESTROY)
//    }
}