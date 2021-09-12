package com.zhr.mvvm.taobao

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhr.mvvm.domain.MapData
import kotlinx.coroutines.launch

class OnSaleViewModel : ViewModel() {

    // 设置当前页
    private var currentPage = DEFAULT_PAGE
    private val TAG: String = "OnSaleViewModel"

    val contentList = MutableLiveData<List<MapData>>()
    val loadState = MutableLiveData<LoadState>()

    companion object {
        // 默认为第一页
        const val DEFAULT_PAGE = 1

        // 默认网络访问成功常量
        const val CODE_SUCCESS = 10000
    }

    private val onSaleRepository by lazy {
        OnSaleRepository()
    }

    /**
     * 加载页面数据
     */
    fun loadContent() {
        listContentByPage(currentPage)
    }

    /**
     * 加载更多数据
     */
    fun loadMore() {

    }

    private fun listContentByPage(page: Int) {
        loadState.postValue(LoadState.LOADING)
        // 使用挂起的方式，免去了回调  --> 协程
        viewModelScope.launch {
            try {// 类似于model.getOnSaleList() 获取返回值
                val onSaleList = onSaleRepository.getOnSaleList(page)
                Log.i(TAG, "onSaleList.size: ${onSaleList.tbkBgOptimusMaterialResponse.resultList.mapData.size}")
                // 在ViewModel里面通过postValue对数据进行观察
                contentList.postValue(onSaleList.tbkBgOptimusMaterialResponse.resultList.mapData)
                if(onSaleList.tbkBgOptimusMaterialResponse.resultList.mapData.isEmpty()){
                    loadState.postValue(LoadState.EMPTY)
                }else {
                    loadState.postValue(LoadState.SUCCESS)
                }
//                val resultData = onSaleRepository.getOnSaleList(page)
//                if (resultData.code == CODE_SUCCESS) {
//                    Log.i(TAG, "onSaleList.size: ${resultData.data?.tbkBgOptimusMaterialResponse?.resultList?.mapData?.size}")
//                }
            } catch (e: Exception) {
                loadState.postValue(LoadState.ERROR)
            }
        }
    }
}



























