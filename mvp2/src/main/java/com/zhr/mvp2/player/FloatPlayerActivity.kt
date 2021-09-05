package com.zhr.mvp2.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhr.mvp2.R
import kotlinx.android.synthetic.main.activity_player.*

/**
 * 实现功能：模拟点击FloatingActionButton按钮实现播放和暂停功能
 */
class FloatPlayerActivity : AppCompatActivity() {

    private val TAG: String = "FloatPlayerActivity"

    private val playerPresenter by lazy {
        PlayerPresenter.getInstance
    }

    private val player by lazy {
        Player()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_float_player)
        initListener()
        initDataListener()
    }

    private fun initListener() {
        btnPlayOrPause.setOnClickListener {
            playerPresenter.playOrPause()
        }
    }

    private fun initDataListener() {
        // 数据驱动开发 监听数据变化 有变化就执行xxx
        playerPresenter.currentPlayState.addListener {
            when(it){
                PlayerPresenter.PlayState.NONE -> btnPlayOrPause.text = "▷"
                PlayerPresenter.PlayState.PLAYING -> btnPlayOrPause.text = "||"
                PlayerPresenter.PlayState.PAUSE -> btnPlayOrPause.text = "▷"
            }
        }
    }
}