package com.zhr.mvp1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_float_player.*
import kotlin.random.Random

/**
 * 实现功能：模拟点击FloatingActionButton按钮实现播放和暂停功能
 * 如果想在这个类中，点击按钮去暂停或者播放歌曲，需要重写多个方法，只需要用到其中的两个方法
 * （但我自己改造的代码，没有很多多余的代码，三个方法都用上了 ^_^）
 * 希望能解决这个问题，引出了：数据驱动开发的概念（而不是UI驱动开发了）
 */
class FloatPlayerActivity : AppCompatActivity(), IPlayerCallback {

    private val TAG: String = "FloatPlayerActivity"
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
        setContentView(R.layout.activity_float_player)
        initData()
        initListener()
    }

    private fun initData() {
        playerPresenter.setPlayerCallback(this)
        playerPresenter.getSongs()
    }

    private fun initListener() {
        btnPlayOrPause.setOnClickListener {
            playerPresenter.playOrPause()
        }
    }

    override fun toContinuePlay() {
        btnPlayOrPause.text = "||"
        player.play(songsBean)
    }

    override fun toRandomPlay() {
        btnPlayOrPause.text = "||"
        // 这条正在播放的歌曲数据 应该是其他页面传过来的 不是自己生成的 这里就当演示了
        songsBean = songsList!![Random.nextInt(songsList!!.size)]
        player.play(songsBean)
    }

    override fun toPause() {
        btnPlayOrPause.text = "▷"
        player.pause(songsBean)
    }

    override fun getSongs(songsList: MutableList<SongsBean>?) {
        this.songsList = songsList
    }
}