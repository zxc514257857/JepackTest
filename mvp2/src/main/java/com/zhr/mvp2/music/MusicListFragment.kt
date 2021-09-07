package com.zhr.mvp2.music

import com.zhr.mvp2.base.BaseFragment

class MusicListFragment : BaseFragment() {

    private val musicPresenter by lazy {
        MusicPresenter(this)
    }

//    init {
//        lifecycleProvider.addLifeListener(musicPresenter)
//    }
}