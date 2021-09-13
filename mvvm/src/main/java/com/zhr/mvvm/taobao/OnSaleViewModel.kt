package com.zhr.mvvm.taobao

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhr.mvvm.domain.MapData
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * ViewModel 就类似于Presenter
 * ?.  安全调用符
 *  person?.run()   if(person != null)  person.run()    if(person == null)  null
 * ?:  为null选择符
 *  person1?:person2    if(person1 != null)   person1     if(person1 == null)   person2
 */
class OnSaleViewModel() : ViewModel() {

    // 设置当前页
    private var currentPage = DEFAULT_PAGE
    private val TAG: String = "OnSaleViewModel"

    val contentList = MutableLiveData<MutableList<MapData>>()
    val loadState = MutableLiveData<LoadState>()
    val loadMoreState = MutableLiveData<LoadMoreState>()
    val refreshState = MutableLiveData<RefreshState>()

    // 加载类型为：默认加载0，上滑加载更多加载1，下拉刷新加载2
    private var loadType: Int = 0

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
        // 加载方式为默认加载
        loadType = 0
        listContentByPage(currentPage)
    }

    /**
     * 上滑加载更多
     */
    fun loadMore() {
        Log.i(TAG, "loadMore: ")
        // 加载下一个页面
        currentPage++
        // 加载方式为上滑加载更多
        loadType = 1
        listContentByPage(currentPage)
    }

    /**
     * 下拉刷新
     */
    fun refresh() {

        Log.i(TAG, "refresh: ")
        // 加载方式为下拉刷新
        loadType = 2
        // 这个接口只有1-4页有数据，那就随机加载一到四页的数据
        listContentByPage(Random.nextInt(4) + 1)
    }

    private fun listContentByPage(page: Int) {
        Log.i(TAG, "currentPage:$page")
        if (loadType == 0) {
            loadState.postValue(LoadState.LOADING)
        } else if (loadType == 1) {
            loadMoreState.postValue(LoadMoreState.LOADMORE_LOADING)
        } else if (loadType == 2) {
            refreshState.postValue(RefreshState.REFRESH_LOADING)
        }
        // 使用挂起的方式，免去了回调  --> 协程
        viewModelScope.launch {
            try {
                // 类似于model.getOnSaleList() 获取返回值
                val onSaleList = onSaleRepository.getOnSaleList(page)
                Log.i(TAG,
                    "onSaleList.size: ${onSaleList.tbkBgOptimusMaterialResponse.resultList.mapData.size}")
                // 数据叠加 contentList每次都把上一次的数据添加到集合中
                val oldValue = contentList.value ?: mutableListOf()
                if (loadType == 0 || loadType == 1) {
                    oldValue.addAll(onSaleList.tbkBgOptimusMaterialResponse.resultList.mapData)
                } else if (loadType == 2) {
                    oldValue.addAll(0, onSaleList.tbkBgOptimusMaterialResponse.resultList.mapData)
                }
                contentList.postValue(oldValue)
//                // 在ViewModel里面通过postValue对数据进行观察
//                contentList.postValue(onSaleList.tbkBgOptimusMaterialResponse.resultList.mapData)
                if (onSaleList.tbkBgOptimusMaterialResponse.resultList.mapData.isEmpty()) {
                    if (loadType == 0) {
                        loadState.postValue(LoadState.EMPTY)
                    } else if (loadType == 1) {
                        loadMoreState.postValue(LoadMoreState.LOADMORE_EMPTY)
                    } else if (loadType == 2) {
                        refreshState.postValue(RefreshState.REFRESH_EMPTY)
                    }
                } else {
                    if (loadType == 0) {
                        loadState.postValue(LoadState.SUCCESS)
                    } else if (loadType == 1) {
                        loadMoreState.postValue(LoadMoreState.LOADMORE_SUCCESS)
                    } else if (loadType == 2) {
                        refreshState.postValue(RefreshState.REFRESH_SUCCESS)
                    }
                }
//                val resultData = onSaleRepository.getOnSaleList(page)
//                if (resultData.code == CODE_SUCCESS) {
//                    Log.i(TAG, "onSaleList.size: ${resultData.data?.tbkBgOptimusMaterialResponse?.resultList?.mapData?.size}")
//                }
            } catch (e: Exception) {
                e.printStackTrace()
                // 上滑加载更多，没有更多内容的时候会有一个空指针过来，需要在这里处理一下
                // 没有更多数据的时候，其实是需要根据页码进行判断，为最后一页的时候就加载LOADMORE_EMPTY
                if (e is NullPointerException) {
                    if (loadType == 0) {
                        loadState.postValue(LoadState.EMPTY)
                    } else if (loadType == 1) {
                        loadMoreState.postValue(LoadMoreState.LOADMORE_EMPTY)
                    } else if (loadType == 2) {
                        refreshState.postValue(RefreshState.REFRESH_EMPTY)
                    }
                } else {
                    if (loadType == 0) {
                        loadState.postValue(LoadState.ERROR)
                    } else if (loadType == 1) {
                        loadMoreState.postValue(LoadMoreState.LOADMORE_ERROR)
                    } else if (loadType == 2) {
                        refreshState.postValue(RefreshState.REFRESH_ERROR)
                    }
                }
                // 如果加载失败则页面累加失败，后面再次加载
                currentPage--
            }
        }
    }
}



























