package com.zhr.mvvm.taobao

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.zhr.mvvm.R
import kotlinx.android.synthetic.main.activity_on_error.*
import kotlinx.android.synthetic.main.activity_on_sale.*

/**
 * https://www.bilibili.com/video/BV15f4y1B7nT
 * 淘宝 恰饭联盟 案例
 * View  视图类
 * ViewMdel: LiveData  ViewModel 就是逻辑类，跟我们的Presenter一样。它的生命周期只有一个就是销毁的时候调用onCleared
 * Model: Room、Retrofit   数据提供层
 */
@Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
class OnSaleActivity : AppCompatActivity() {

    private val TAG: String = "OnSaleActivity"
    private var preLoadState = LoadState.LOADING

    private val viewModel by lazy {
        ViewModelProvider(this).get(OnSaleViewModel::class.java)
    }

    private val onSaleListAdapter by lazy {
        OnSaleListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_sale)
        initView()
        initObserver()
    }

    private fun initView() {
        // run表示 你这个为第一个的话 可以不写  后面测试可以不为第一个
        // apply 也表示这个意思 并且它有返回值  后面测试可以不为第一个
        // let 里面的值要用it表示，可以表示在第一个的 也可以表示其他位置的
        rvContentList.run {
            adapter = onSaleListAdapter
            layoutManager = LinearLayoutManager(this@OnSaleActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State,
                ) {
                    outRect.run {
                        // android 代码里面默认的长度单位是px，默认的时间单位是ms
                        val padding = ConvertUtils.px2dp(8f)
                        top = padding
                        bottom = padding
                        left = padding
                        right = padding
                    }
                }
            })
        }
        viewReload.setOnClickListener {
            viewModel.loadContent()
        }
    }

    private fun initObserver() {
        viewModel.apply {
            contentList.observe(this@OnSaleActivity, {
                Log.i(TAG, "mapData: $it")
                onSaleListAdapter.setData(it)
            })
            loadState.observe(this@OnSaleActivity, {
                Log.i(TAG, "loadState: $it")
                // 除暴方式
//                hideAll()
                // 优雅方式
                hideOne()
                when (it) {
                    LoadState.LOADING -> {
                        includeLoading.visibility = View.VISIBLE
                        Log.i(TAG, "LoadState.LOADING")
                    }
                    LoadState.EMPTY -> {
                        includeEmpty.visibility = View.VISIBLE
                        Log.i(TAG, "LoadState.EMPTY")
                    }
                    LoadState.ERROR -> {
                        includeError.visibility = View.VISIBLE
                        Log.i(TAG, "LoadState.ERROR")
                    }
                    LoadState.SUCCESS -> {
                        rvContentList.visibility = View.VISIBLE
                        Log.i(TAG, "LoadState.SUCCESS")
                    }
                }
                preLoadState = it
            })
        }.loadContent()
    }

    private fun hideOne() {
        when (preLoadState) {
            LoadState.LOADING -> includeLoading.visibility = View.GONE
            LoadState.EMPTY -> includeEmpty.visibility = View.GONE
            LoadState.ERROR -> includeError.visibility = View.GONE
            LoadState.SUCCESS -> rvContentList.visibility = View.GONE
        }
    }

    private fun hideAll() {
        rvContentList.visibility = View.GONE
        includeEmpty.visibility = View.GONE
        includeError.visibility = View.GONE
        includeLoading.visibility = View.GONE
    }
}