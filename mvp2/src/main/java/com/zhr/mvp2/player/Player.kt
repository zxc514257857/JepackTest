package com.zhr.mvp2.player

import android.util.Log

class Player {

    private val TAG: String = "Player"

    fun play(currentSong: SongsBean?) {
        Log.i(TAG, "播放器播放歌曲:${currentSong?.title}")
    }

    fun pause(currentSong: SongsBean?) {
        Log.i(TAG, "播放器暂停歌曲:${currentSong?.title}")
    }
}