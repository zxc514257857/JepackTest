package com.zhr.mvp2

class PlayerPresenter private constructor() {

    private var playerCallback: IPlayerCallback? = null
    private var currentPlayState: PlayState? = PlayState.NONE
    private var currentSong: SongsBean? = null

    companion object {
        @JvmStatic
        val getInstance by lazy {
            PlayerPresenter()
        }
    }

    private val playerModel by lazy {
        PlayerModel()
    }

    private val player by lazy {
        Player()
    }

    // 播放器当前三种状态：播放中，暂停中以及无内容中
    enum class PlayState {
        PLAYING, PAUSE, NONE
    }

    fun setPlayerCallback(playerCallback: IPlayerCallback) {
        this.playerCallback = playerCallback
    }

    fun playOrPause() {
        if(currentSong == null){
            currentSong = playerModel.getSongById()
        }
        player.play(currentSong)




        when (currentPlayState) {
            // 当前为无内容状态 -> 那就随机播放歌曲
            PlayState.NONE -> {
                playerCallback?.playRandomSong()
                currentPlayState = PlayState.PLAYING
            }
            // 当前为播放中状态 -> 那就暂停播放
            PlayState.PLAYING -> {
                playerCallback?.pauseSong()
                currentPlayState = PlayState.PAUSE
            }
            // 当前为暂停中状态 -> 那就开始播放本首歌曲
            PlayState.PAUSE -> {
                playerCallback?.playContinueSong()
                currentPlayState = PlayState.PLAYING
            }
        }
    }

    fun playPrevious() {
        playerCallback?.playRandomSong()
        currentPlayState = PlayState.PLAYING
    }

    fun playNext() {
        playerCallback?.playRandomSong()
        currentPlayState = PlayState.PLAYING
    }

    fun getSongs() {
        val songsList = playerModel.requestSongs()
        playerCallback?.getSongs(songsList)
    }
}