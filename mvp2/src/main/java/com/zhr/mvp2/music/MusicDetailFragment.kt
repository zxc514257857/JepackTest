package com.zhr.mvp2.music

import com.zhr.mvp2.base.BaseFragment

class MusicDetailFragment : BaseFragment() {

//    private val lifecycleProvider by lazy {
//        LifecycleProvider()
//    }

    private val musicPresenter by lazy {
        MusicPresenter(this)
    }

//    init {
//        lifecycleProvider.addLifeListener(musicPresenter)
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        lifecycleProvider.makeLifeState(LifeState.CREATE)
//    }
//
//    override fun onStart() {
//        super.onStart()
//        lifecycleProvider.makeLifeState(LifeState.START)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        lifecycleProvider.makeLifeState(LifeState.RESUME)
//    }
//
//    override fun onPause() {
//        super.onPause()
//        lifecycleProvider.makeLifeState(LifeState.PAUSE)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        lifecycleProvider.makeLifeState(LifeState.STOP)
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        lifecycleProvider.makeLifeState(LifeState.DESTROY)
//    }
}