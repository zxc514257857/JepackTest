package com.zhr.mvp1

interface IPlayerCallback {

    fun toContinuePlay()
    fun toRandomPlay()
    fun toPause()
    fun getSongs(songsList: MutableList<SongsBean>?)
}