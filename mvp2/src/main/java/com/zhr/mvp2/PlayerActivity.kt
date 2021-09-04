package com.zhr.mvp2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    private val TAG: String = "PlayerActivity"

    private val playerPresenter by lazy {
        PlayerPresenter.getInstance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initListener()
        initDataListener()
    }

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