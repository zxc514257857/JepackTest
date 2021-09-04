package com.zhr.mvp1

import android.util.Log

// 私有构造方法，无法通过构造方法创建对象
class PlayerPresenter private constructor() {

    private var playerCallback: IPlayerCallback? = null
    private var currentPlayState = PlayState.NONE

    // PlayerPresenter 单例模式
    // 1、私有构造方法 -- 不能通过构造方法创建对象
    // 2、创建伴生对象（类似java中的static） -- 可以通过类名.进行调用
    // 3、创建懒加载的对象 -- 只能创建一个对象
    companion object{
        val getInstance by lazy{
            PlayerPresenter()
        }
    }

    private val playerModel by lazy {
        PlayerModel()
    }

    // 枚举类写法
    enum class PlayState {
        PLAYING, PAUSE, LOADING, NONE
    }

    fun setPlayerCallback(playerCallback: IPlayerCallback) {
        this.playerCallback = playerCallback
    }

    fun playOrPause() {
        when (currentPlayState) {
            PlayState.NONE -> {
                playerCallback?.toRandomPlay()
                currentPlayState = PlayState.PLAYING
            }
            PlayState.LOADING -> {
                playerCallback?.toContinuePlay()
                currentPlayState = PlayState.PLAYING
            }
            PlayState.PAUSE -> {
                playerCallback?.toContinuePlay()
                currentPlayState = PlayState.PLAYING
            }
            PlayState.PLAYING -> {
                playerCallback?.toPause()
                currentPlayState = PlayState.PAUSE
            }
        }
    }

    fun playPrevious() {
        playerCallback?.toRandomPlay()
        currentPlayState = PlayState.PLAYING
    }

    fun playNext() {
        playerCallback?.toRandomPlay()
        currentPlayState = PlayState.PLAYING
    }

    fun getSongs(){
        val songsList = playerModel.requestSongs()
        playerCallback?.getSongs(songsList)
    }
}