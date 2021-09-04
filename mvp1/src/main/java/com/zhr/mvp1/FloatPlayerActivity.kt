package com.zhr.mvp1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_float_player.*

// 如果想在这个类中，点击按钮去暂停或者播放歌曲，需要重写多个方法，只需要用到其中的两个方法
// 希望能解决这个问题，引出了：数据驱动开发的概念（而不是UI驱动开发了）
class FloatPlayerActivity : AppCompatActivity(), IPlayerCallback {

    private val playerPresenter by lazy {
        PlayerPresenter.getInstance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_float_player)
        initListener()
    }

    private fun initListener() {
        playerPresenter.setPlayerCallback(this)
        btnPlayOrPause.setOnClickListener {
            playerPresenter.playOrPause()
        }
    }

    override fun toContinuePlay() {
        btnPlayOrPause.text = "||"
    }

    override fun toRandomPlay() {
    }

    override fun toPause() {
        btnPlayOrPause.text = "▷"
    }

    override fun getSongs(songsList: MutableList<SongsBean>?) {
    }
}