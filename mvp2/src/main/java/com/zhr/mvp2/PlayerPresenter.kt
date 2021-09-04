package com.zhr.mvp2

class PlayerPresenter private constructor() {

    // 对这两个数据让 DataListenController进行了一个包裹， 对这两个数据进行监听
    var currentPlayState = DataListenController<PlayState>()
    var currentSong = DataListenController<SongsBean>()
    private val TAG: String = "PlayerPresenter"

    companion object {
        @JvmStatic
        val getInstance by lazy {
            PlayerPresenter()
        }
    }

    // 播放器当前三种状态：播放中，暂停中以及无内容中
    enum class PlayState {
        PLAYING, PAUSE, NONE
    }

    private val playerModel by lazy {
        PlayerModel()
    }

    private val player by lazy {
        Player()
    }

    fun playOrPause() {
        when (currentPlayState.value) {
            // 当前为无内容状态 -> 那就随机播放歌曲
            PlayState.NONE -> {
                playSongs()
                currentPlayState.value = PlayState.PLAYING
            }
            // 当前为播放中状态 -> 那就暂停播放
            PlayState.PLAYING -> {
                // 在播放暂停这里不去改变当前歌曲数据 只改变播放状态数据
                player.pause(currentSong.value)
                currentPlayState.value = PlayState.PAUSE
            }
            // 当前为暂停中状态 -> 那就开始播放本首歌曲
            PlayState.PAUSE -> {
                // 在播放暂停这里不去改变当前歌曲数据 只改变播放状态数据
                player.play(currentSong.value)
                currentPlayState.value = PlayState.PLAYING
            }
            else -> {
                playSongs()
                currentPlayState.value = PlayState.PLAYING
            }
        }
    }

    private fun playSongs() {
        currentSong.value = playerModel.getSongById()
        player.play(currentSong.value)
    }

    fun playPrevious() {
        playSongs()
        currentPlayState.value = PlayState.PLAYING
    }

    fun playNext() {
        playSongs()
        currentPlayState.value = PlayState.PLAYING
    }
}