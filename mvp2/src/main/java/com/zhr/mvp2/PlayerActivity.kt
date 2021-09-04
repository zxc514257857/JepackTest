package com.zhr.mvp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_player.*
import kotlin.random.Random

class PlayerActivity : AppCompatActivity(), IPlayerCallback {

    private var songsList: MutableList<SongsBean>? = null

    private val playerPresenter by lazy {
        PlayerPresenter.getInstance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initData()
        initListener()
        initDataListener()
    }

    private fun initData() {
        playerPresenter.setPlayerCallback(this)
        playerPresenter.getSongs()
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

    private fun initDataListener() {

    }

    override fun getSongs(songsList: MutableList<SongsBean>) {
        this.songsList = songsList
    }

    override fun playRandomSong() {
        btnPlayOrPause.text = "||"
        val songsBean = songsList?.get(Random.nextInt(songsList!!.size))
        tvSongTitle.text = songsBean?.title
        ivSongCover.setImageResource(songsBean?.pics!!)
    }

    override fun playContinueSong() {
        btnPlayOrPause.text = "||"
    }

    override fun pauseSong() {
        btnPlayOrPause.text = "▷"
    }
}