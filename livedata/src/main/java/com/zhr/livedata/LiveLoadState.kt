package com.zhr.livedata

import androidx.lifecycle.LiveData

class LiveLoadState private constructor(): LiveData<MusicPresenter.LoadState>() {

    // value设置在父类中是protected的 需要去重写它
    public override fun postValue(value: MusicPresenter.LoadState?) {
        super.postValue(value)
    }

    public override fun setValue(value: MusicPresenter.LoadState?) {
        super.setValue(value)
    }

    companion object{
        val instance by lazy {
            LiveLoadState()
        }
    }
}