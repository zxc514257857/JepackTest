package com.zhr.mvp1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_player.*
import kotlin.random.Random

/**
 * 实现功能: 点击上一首、下一首、播放、暂停 处理对应的逻辑
 * 之前通过界面去写代码的方式叫做UI驱动开发。通过测试用例以及测试结果驱动的方式叫测试驱动开发
 * 后面我们还会使用到数据驱动开发
 */
class PlayerActivity : AppCompatActivity(), IPlayerCallback {

    private val TAG: String = "PlayerActivity"
    private var songsList: MutableList<SongsBean>? = null
    private var songsBean: SongsBean? = null

    private val playerPresenter by lazy {
        PlayerPresenter.getInstance
    }

    private val player by lazy {
        Player()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initData()
        initListener()
    }

    private fun initData() {
        playerPresenter.setPlayerCallback(this)
        playerPresenter.getSongs()
    }

    override fun getSongs(songsList: MutableList<SongsBean>?) {
        this.songsList = songsList
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

    override fun toContinuePlay() {
        btnPlayOrPause.text = "||"
        player.play(songsBean)
    }

    override fun toRandomPlay() {
        btnPlayOrPause.text = "||"
        songsBean = songsList!![Random.nextInt(songsList!!.size)]
        ivSongCover.setImageResource(songsBean?.pics!!)
        tvSongTitle.text = songsBean?.title
        player.play(songsBean)
    }

    override fun toPause() {
        btnPlayOrPause.text = "▷"
        player.pause(songsBean)
    }
}