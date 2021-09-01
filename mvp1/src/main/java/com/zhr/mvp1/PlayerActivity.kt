package com.zhr.mvp1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_player.*
import kotlin.random.Random

/**
 * 实现功能: 点击上一首、下一首、播放暂停 处理对应的逻辑
 * 这种开发方式叫UI驱动开发，后面我们还会使用到数据驱动开发
 */
class PlayerActivity : AppCompatActivity(), PlayerPresenter.PlayerCallback {

    private var songsList: MutableList<SongsBean>? = null

    private val playerPresenter by lazy {
        PlayerPresenter.getInstance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initData()
        initListener()
    }

    private fun initData() {
        songsList = mutableListOf()
        val songsBean1 = SongsBean()
        songsBean1.title = "STAY"
        songsBean1.pics = R.mipmap.pic1
        songsList?.add(songsBean1)

        val songsBean2 = SongsBean()
        songsBean2.title = "初恋"
        songsBean2.pics = R.mipmap.pic2
        songsList?.add(songsBean2)

        val songsBean3 = SongsBean()
        songsBean3.title = "还是会想你"
        songsBean3.pics = R.mipmap.pic3
        songsList?.add(songsBean3)

        val songsBean4 = SongsBean()
        songsBean4.title = "晚风"
        songsBean4.pics = R.mipmap.pic4
        songsList?.add(songsBean4)

        val songsBean5 = SongsBean()
        songsBean5.title = "起风了"
        songsBean5.pics = R.mipmap.pic5
        songsList?.add(songsBean5)

        val songsBean6 = SongsBean()
        songsBean6.title = "雨爱"
        songsBean6.pics = R.mipmap.pic6
        songsList?.add(songsBean6)

        val songsBean7 = SongsBean()
        songsBean7.title = "天上飞"
        songsBean7.pics = R.mipmap.pic7
        songsList?.add(songsBean7)

        val songsBean8 = SongsBean()
        songsBean8.title = "哪里都是你"
        songsBean8.pics = R.mipmap.pic8
        songsList?.add(songsBean8)

        val songsBean9 = SongsBean()
        songsBean9.title = "错位时空"
        songsBean9.pics = R.mipmap.pic9
        songsList?.add(songsBean9)

        val songsBean10 = SongsBean()
        songsBean10.title = "水星记"
        songsBean10.pics = R.mipmap.pic10
        songsList?.add(songsBean10)
    }

    private fun initListener() {
        playerPresenter.setPlayerCallback(this)
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
    }

    override fun toRandomPlay() {
        btnPlayOrPause.text = "||"
        val songsBean = songsList!![Random.nextInt(songsList!!.size)]
        ivSongCover.setImageResource(songsBean.pics!!)
        tvSongTitle.text = songsBean.title
    }

    override fun toPause() {
        btnPlayOrPause.text = "▷"
    }
}