package com.zhr.livedata

import android.os.SystemClock
import android.util.Log

class MusicModel private constructor() {

    private val TAG: String = "MusicModel"

    companion object {
        val getInstance by lazy {
            MusicModel()
        }
    }

    fun getMusicByPage(page: Int, size: Int, callback: MusicDataCallback) {
        Thread {
            callback.onLoading()
            val musicList = arrayListOf<Music>()
            for (i in 0 until size) {
                val music = Music()
                music.title = "亲爱的旅人:::$i"
                music.pics = R.mipmap.ic_launcher
                musicList.add(music)
                SystemClock.sleep(100)
            }
            callback.onSuccess(musicList)
            Log.i(TAG, "getMusicByPage: ")
        }.start()
    }

    interface MusicDataCallback {
        fun onLoading()
        fun onSuccess(musicData: List<Music>)
        fun onFail(code: Int, msg: String)
    }
}