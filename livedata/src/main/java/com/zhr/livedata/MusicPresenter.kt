package com.zhr.livedata

import android.util.Log
import androidx.lifecycle.MutableLiveData

class MusicPresenter private constructor() {

    private val TAG: String = "MusicPresenter"
//    private var musicCallback: MusicCallback? = null
    val liveMusicList = MutableLiveData<List<Music>>()
    val liveLoadState = MutableLiveData<LoadState>()

    enum class LoadState {
        LOADING, LOAD_SUCCESS, LOAD_FAIL
    }

    companion object {
        val getInstance by lazy {
            MusicPresenter()
        }
    }

    // 回调方式一 ： 可以在这里回调，也可以单独创建接口回调
//    fun getMusicByPage(page: Int, size: Int, callback: MusicCallback) {
//        // 一层层通过接口回调的方式，将数据从model层回调到presenter层，再回调到view层
//        MusicModel.getInstance.getMusicByPage(page, size, object : MusicModel.MusicDataCallback {
//
//            override fun onLoading() {
//                callback.loading()
//                Log.i(TAG, "loading: ")
//            }
//
//            override fun onSuccess(musicData: List<Music>) {
//                callback.getMusicSuccess(musicData)
//                Log.i(TAG, "getMusicSuccess: $musicData")
//            }
//
//            override fun onFail(code: Int, msg: String) {
//                callback.getMusicFail()
//                Log.i(TAG, "getMusicFail: ")
//            }
//        })
//    }

    // 回调方式二 ： 在外部创建回调方法
    fun getMusicByPage(page: Int, size: Int) {
        // 一层层通过接口回调的方式，将数据从model层回调到presenter层，再回调到view层
        MusicModel.getInstance.getMusicByPage(page, size, object : MusicModel.MusicDataCallback {

            override fun onLoading() {
//                musicCallback?.loading()
                liveLoadState.postValue(LoadState.LOADING)
                Log.i(TAG, "loading: ")
            }

            override fun onSuccess(musicData: List<Music>) {
//                musicCallback?.getMusicSuccess(musicData)
                liveLoadState.postValue(LoadState.LOAD_SUCCESS)
                // 我想要观察 musicData，我就把 musicData赋值给livedat
                liveMusicList.postValue(musicData)
                Log.i(TAG, "getMusicSuccess: $musicData")
            }

            override fun onFail(code: Int, msg: String) {
//                musicCallback?.getMusicFail()
                liveLoadState.postValue(LoadState.LOAD_FAIL)
                Log.i(TAG, "getMusicFail: ")
            }
        })
    }


//    fun regiestCallback(callback: MusicCallback) {
//        this.musicCallback = callback
//    }

    interface MusicCallback {
        fun getMusicSuccess(musicData: List<Music>)
        fun getMusicFail()
        fun loading()
    }
}