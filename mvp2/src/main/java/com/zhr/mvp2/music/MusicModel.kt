package com.zhr.mvp2.music

import com.zhr.mvp2.R

class MusicModel {

    fun getMusicByPage(page: Int, size: Int, callback: RequestMusicCallback) {
        // 在子线程进行网络请求等耗时性操作
        Thread{
            val musicList = mutableListOf<Music>()
            for(i in 0 until size){
                val music = Music()
                music.title = "图书馆$i"
                music.pics = R.mipmap.pic1
                musicList.add(music)
            }
            callback.onSuccess(musicList)
        }.start()
    }

    interface RequestMusicCallback {
        fun onSuccess(musicList: List<Music>?)
        fun onFail(msg: String, code: Int)
    }
}