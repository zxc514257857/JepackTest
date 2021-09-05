package com.zhr.mvp2.music

import android.util.Log
import com.zhr.mvp2.lifecycle.ILifeCycle
import com.zhr.mvp2.player.DataListenController

class MusicPresenter : ILifeCycle {

    private val TAG: String = "MusicPresenter"

    // 通过 DataListenController进行监听 音乐数据变化
    val musicList = DataListenController<List<Music>>()
    private val page: Int = 1
    private val size: Int = 30

    private val musicModel by lazy {
        MusicModel()
    }

    fun getMusic() {
        // 从model层获取音乐列表
        musicModel.getMusicByPage(page, size, object : MusicModel.requestMusicCallback {
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

    override fun onCreate() {}

    override fun onStart() {
        Log.i(TAG, "GPS监听打开")
    }

    override fun onResume() {}

    override fun onPause() {}

    override fun onStop() {
        Log.i(TAG, "GPS监听关闭")
    }

    override fun onDestroy() {}
}