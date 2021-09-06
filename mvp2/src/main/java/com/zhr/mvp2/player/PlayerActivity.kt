package com.zhr.mvp2.player

import android.os.Bundle
import android.util.Log
import com.zhr.mvp2.R
import com.zhr.mvp2.base.BaseActivity
import com.zhr.mvp2.music.MusicPresenter
import kotlinx.android.synthetic.main.activity_player.*

/**
 * 实现功能: 点击上一首、下一首、播放、暂停 处理对应的逻辑
 */
class PlayerActivity : BaseActivity() {

    private val TAG: String = "PlayerActivity"

    private val playerPresenter by lazy {
        PlayerPresenter.getInstance
    }

    private val musicPresenter by lazy {
        MusicPresenter()
    }

    init {
        // 把这两个实现了生命周期接口的方法加入监听
//        addLifeListener(playerPresenter)
//        addLifeListener(musicPresenter)
        lifeCycleProvider.addLifeListener(playerPresenter)
        lifeCycleProvider.addLifeListener(musicPresenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initListener()
        initDataListener()
//        musicPresenter.onStart()
    }

//    override fun onStart() {
//        super.onStart()
//        musicPresenter.onStart()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        musicPresenter.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        musicPresenter.onPause()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        musicPresenter.onStop()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        musicPresenter.onDestroy()
//    }

    private fun initListener() {
        btnPlayOrPause.setOnClickListener {
            playerPresenter.playOrPause()
        }
        btnPlayPrevious.setOnClickListener {
            playerPresenter.playPrevious()
        }
        btnPlayNext.setOnClickListener {
            playerPresenter.playNext()
        }
    }

    /**
     * 对数据进行监听
     */
    private fun initDataListener() {
        playerPresenter.currentSong.addListener {
            Log.i(TAG, "当前歌曲回调：$it")
            // 数据变化，监听。更新UI
            tvSongTitle.text = it?.title
            ivSongCover.setImageResource(it?.pics!!)
        }
        playerPresenter.currentPlayState.addListener {
            Log.i(TAG, "当前歌曲状态回调: $it")
            when (it) {
                PlayerPresenter.PlayState.NONE -> {
                    btnPlayOrPause.text = "▷"
                }
                PlayerPresenter.PlayState.PLAYING -> {
                    btnPlayOrPause.text = "||"
                }
                PlayerPresenter.PlayState.PAUSE -> {
                    btnPlayOrPause.text = "▷"
                }
            }
        }
    }
}