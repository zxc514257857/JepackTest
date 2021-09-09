package com.zhr.livedata

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_music.*
import kotlin.random.Random

/**
 * 实现功能：获取音乐列表数据，显示在按钮上，通过livedata进行数据监听
 */
//class MusicActivity : AppCompatActivity() {
class MusicActivity : AppCompatActivity(), MusicPresenter.MusicCallback {

    private var handler: Handler? = null
    private val TAG: String = "MusicActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        handler = Handler()
        initViewListener()
        initDataListener()
    }

    private fun initViewListener() {
        btnGetMusic.setOnClickListener {
            Log.i(TAG, "initViewListener: ")
            // 回调方式一  m层回调p层回调v层
//            MusicPresenter.getInstance.getMusicByPage(1, 30, object : MusicPresenter.MusicCallback {
//                override fun getMusicSuccess(musicData: List<Music>) {
//                    // 这里如果进行ui更新的话，注意是否为主线程
//                    if (Looper.getMainLooper().thread == Thread.currentThread()) {
//                        btnGetMusic.text = musicData[Random.nextInt(musicData.size)].title
//                        btnGetMusic.isEnabled = true
//                    } else {
//                        // 如果不为主线程，则需要切换线程
//                        handler?.post {
//                            btnGetMusic.text = musicData[Random.nextInt(musicData.size)].title
//                            btnGetMusic.isEnabled = true
//                        }
//                    }
//                }
//
//                override fun getMusicFail() {
//                    if (Looper.getMainLooper().thread == Thread.currentThread()) {
//                        btnGetMusic.text = "歌曲加载失败..."
//                        btnGetMusic.isEnabled = true
//                    } else {
//                        // 如果不为主线程，则需要切换线程
//                        handler?.post {
//                            btnGetMusic.text = "歌曲加载失败..."
//                            btnGetMusic.isEnabled = true
//                        }
//                    }
//                }
//
//                override fun loading() {
//                    if (Looper.getMainLooper().thread == Thread.currentThread()) {
//                        btnGetMusic.text = "歌曲加载中..."
//                        btnGetMusic.isEnabled = false
//                    } else {
//                        // 如果不为主线程，则需要切换线程
//                        handler?.post {
//                            btnGetMusic.text = "歌曲加载中..."
//                            btnGetMusic.isEnabled = false
//                        }
//                    }
//                }
//            })

            // 回调方式二  m层回调p层回调v层
//            MusicPresenter.getInstance.regiestCallback(this)
//            MusicPresenter.getInstance.getMusicByPage(1, 30)

            // 方式三 livedata
            MusicPresenter.getInstance.getMusicByPage(1, 30)
        }
    }

    private fun initDataListener() {
        // 监听方式一：Observe监听  这种方式通过owner 不需要处理删除监听操作
        MusicPresenter.getInstance.liveMusicList.observe(this, {
            btnGetMusic.text = it[Random.nextInt(it.size)].title
        })
        MusicPresenter.getInstance.liveLoadState.observe(this, {
            when (it) {
                MusicPresenter.LoadState.LOADING -> {
                    btnGetMusic.text = "歌曲加载中..."
                    btnGetMusic.isEnabled = false
                }
                MusicPresenter.LoadState.LOAD_SUCCESS -> {
                    btnGetMusic.isEnabled = true
                }
                MusicPresenter.LoadState.LOAD_FAIL -> {
                    btnGetMusic.text = "歌曲加载失败..."
                    btnGetMusic.isEnabled = true
                }
                else -> {
                }
            }
        })

//        // 监听方式二：observeForerver监听
//        observer = ForeverObserver()
//        // 开始 observeForerver监听
//        MusicPresenter.getInstance.liveMusicList.observeForever(observer)
    }

//    // 后面进行初始化的声明方法
//    lateinit var observer: ForeverObserver
//
//    inner class ForeverObserver : Observer<List<Music>> {
//        override fun onChanged(musicList: List<Music>?) {
//            btnGetMusic.text = musicList!![Random.nextInt(musicList.size)].title
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        // 关闭 observeForerver监听
//        MusicPresenter.getInstance.liveMusicList.removeObserver(observer)
//    }

    override fun getMusicSuccess(musicData: List<Music>) {
        // 这里如果进行ui更新的话，注意是否为主线程
        if (Looper.getMainLooper().thread == Thread.currentThread()) {
            btnGetMusic.text = musicData[Random.nextInt(musicData.size)].title
            btnGetMusic.isEnabled = true
        } else {
            // 如果不为主线程，则需要切换线程
            handler?.post {
                btnGetMusic.text = musicData[Random.nextInt(musicData.size)].title
                btnGetMusic.isEnabled = true
            }
        }
    }

    override fun getMusicFail() {
        if (Looper.getMainLooper().thread == Thread.currentThread()) {
            btnGetMusic.text = "歌曲加载失败..."
            btnGetMusic.isEnabled = true
        } else {
            // 如果不为主线程，则需要切换线程
            handler?.post {
                btnGetMusic.text = "歌曲加载失败..."
                btnGetMusic.isEnabled = true
            }
        }
    }

    override fun loading() {
        if (Looper.getMainLooper().thread == Thread.currentThread()) {
            btnGetMusic.text = "歌曲加载中..."
            btnGetMusic.isEnabled = false
        } else {
            // 如果不为主线程，则需要切换线程
            handler?.post {
                btnGetMusic.text = "歌曲加载中..."
                btnGetMusic.isEnabled = false
            }
        }
    }
}