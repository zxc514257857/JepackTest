package com.zhr.mvp2.music

import android.util.Log
import com.zhr.mvp2.lifecycle.ILifecycle
import com.zhr.mvp2.lifecycle.ILifecycleOwner
import com.zhr.mvp2.player.DataListenController

// 不直接实现ILifecycle了，而是通过内部类实现
//class MusicPresenter : ILifecycle {
class MusicPresenter(lifecycleOwner: ILifecycleOwner) : ILifecycle {
//class MusicPresenter(lifecycleOwner: ILifecycleOwner) {

    private val TAG: String = "MusicPresenter"

    // 通过 DataListenController进行监听 音乐数据变化
    val musicList = DataListenController<List<Music>>()
    private val page: Int = 1
    private val size: Int = 30

    private val musicModel by lazy {
        MusicModel()
    }

//    private val viewLifeImpl by lazy {
//        ViewLifeImpl()
//    }

    init {
        // 将生命周期的监听由activity放到presenter中进行，ILifecycle是由Presenter实现或是由Presenter的内部类实现均可
        // 要有警惕，带括号的声明的时候是否要声明成 by lazy方式
//        lifecycleOwner.getLifecycleProvider().addLifeListener(ViewLifeImpl())
        lifecycleOwner.getLifecycleProvider().addLifeListener(this)
//        lifecycleOwner.getLifecycleProvider().addLifeListener(viewLifeImpl)
    }

    fun getMusic() {
        // 从model层获取音乐列表
        musicModel.getMusicByPage(page, size, object : MusicModel.RequestMusicCallback {
            override fun onSuccess(musicList: List<Music>?) {
                // 将数据赋值给正在监听的数据
                this@MusicPresenter.musicList.value = musicList
                Log.i(TAG, "onSuccess: $musicList")
            }

            override fun onFail(msg: String, code: Int) {
                Log.i(TAG, "onFail: msg=$msg, code=$code")
            }
        })
    }

//    inner class ViewLifeImpl : ILifecycle {

    override fun onCreate() {
    }

    override fun onStart() {
        Log.i(TAG, "GPS监听打开")
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
        Log.i(TAG, "GPS监听关闭")
    }

    override fun onDestroy() {
    }
//    }
}