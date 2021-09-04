package com.zhr.mvp2

interface IPlayerCallback {
    fun playRandomSong()
    fun playContinueSong()
    fun pauseSong()
    fun getSongs(songsList: MutableList<SongsBean>)
}