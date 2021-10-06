package com.example.databinding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.example.databinding.app.AppConstant
import com.example.databinding.domain.LoadState
import com.example.databinding.domain.OnSaleItem
import com.example.databinding.domain.RefreshState
import com.example.databinding.repository.OnSaleRepository
import kotlinx.coroutines.launch

class OnSaleViewModel : ViewModel() {

    private var mCurrentPage = AppConstant.DEFAULT_PAGE

    companion object {
        const val TAG: String = "OnSaleViewModel"
    }

    // 所关心的：加载状态和数据列表
    // 加载状态：Loading、Success、Empty、Error、None
    // 等着给loadState和contentList赋值呢
    val loadState by lazy {
        MutableLiveData<LoadState>()
    }

    // 数据列表：contentList
    val contentList by lazy {
        MutableLiveData<MutableList<OnSaleItem>>()
    }

    val refreshState by lazy {
        MutableLiveData<RefreshState>()
    }

    private val repository by lazy {
        OnSaleRepository()
    }

    fun loadData() {
        loadState.value = LoadState.LOADING
        loadDataByPage(mCurrentPage)
    }

    fun refresh() {
        refreshState.value = RefreshState.LOADING
        val onSaleItem = contentList.value?.get(0)
        val oldValue = contentList.value ?: mutableListOf()
        oldValue.add(0, onSaleItem!!)
        contentList.postValue(oldValue)
        refreshState.value = RefreshState.SUCCESS
    }

    private fun loadDataByPage(page: Int) {
        // 协程写法
        viewModelScope.launch {
            try {
                val result = repository.getOnSaleListByPage(page)
                val resultList = result.data.tbkBgOptimusMaterialResponse.resultList.mapData
                LogUtils.i("resultCode:${result.code},resultMsg:${result.message},loadDataByPage:${resultList.size}")
                if (resultList.isEmpty()) {
                    loadState.value = LoadState.EMPTY
                } else {
                    loadState.value = LoadState.SUCCESS
                    contentList.value = resultList
                }
            } catch (e: Exception) {
                loadState.value = LoadState.ERROR
            }
        }
    }
}