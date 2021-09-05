package com.zhr.mvp2.player

interface IPlayerCallback {
    fun playRandomSong()
    fun playContinueSong()
    fun pauseSong()
    fun getSongs(songsList: MutableList<SongsBean>)
}