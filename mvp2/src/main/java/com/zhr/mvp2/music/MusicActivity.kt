package com.zhr.mvp2.music

import android.os.Bundle
import android.util.Log
import com.zhr.mvp2.R
import com.zhr.mvp2.base.BaseActivity
import kotlinx.android.synthetic.main.activity_music.*
import kotlin.random.Random

/**
 * 实现功能：点击 获取音乐列表按钮，通过model层请求网络，并把结果展示在view层（涉及网络请求切到子线程后线程的切回，涉及到生命周期数据的销毁）
 * DataListener的使用总结：在presenter中赋值，在activity创建监听回调，将值显示在页面上
 *
 * onCreate - 开始创建 不可见
 * onStart - 可见 不可交互
 * onResume - 可见 可交互
 * onPause - 可见 不可交互
 * onStop - 不可见 不可交互
 * onDestroy - 销毁
 *
 * 老师的两个项目：喜马拉雅FM电台 和 恰饭联盟
 */
//class MusicActivity : AppCompatActivity() {
class MusicActivity : BaseActivity() {

    private val TAG: String = "MusicActivity"
//    private val handler = Handler()

    private val musicPresenter by lazy {
        MusicPresenter()
    }

    init {
        // 把此实现了生命周期接口的方法 加入监听
        addListener(musicPresenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        initViewListener()
        initDataListener()
//        musicPresenter.onCreate()
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

    private fun initViewListener() {
        btnGetMusic.setOnClickListener {
            musicPresenter.getMusic()
        }
    }

    /**
     * 监听数据变化
     */
    private fun initDataListener() {
        // currentThread1:main
        Log.i(TAG, "currentThread1:${Thread.currentThread().name}")
        musicPresenter.musicList.addListener {
            // currentThread2:Thread-2  表示这里是子线程
            Log.i(TAG, "currentThread2:${Thread.currentThread().name}")
            // 线程切换 或者直接在block invoke的时候进行线程切换
//            handler.post {
            it?.let {
                // currentThread3:main  表示这里为主线程 切换线程成功
                Log.i(TAG, "currentThread3:${Thread.currentThread().name}")
                val random = Random.nextInt(it.size)
                // 在子线程中如何能够更新UI？
                btnGetMusic.text = it[random].title
                // SetTextI18n 字母和字符串混用，会出现这样一个问题，表示直接在代码中写，未进行国际化操作
                Log.i(TAG, "random: ${it[random].pics!!}")
            }
//            }
        }
    }
}