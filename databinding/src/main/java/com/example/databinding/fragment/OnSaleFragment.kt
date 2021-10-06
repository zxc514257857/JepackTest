package com.example.databinding.fragment

import android.graphics.Rect
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import com.example.databinding.R
import com.example.databinding.adapter.OnSaleListAdapter
import com.example.databinding.base.BaseVMFragment
import com.example.databinding.databinding.FragmentOnSaleBinding
import com.example.databinding.domain.LoadState
import com.example.databinding.domain.RefreshState
import com.example.databinding.viewmodel.OnSaleViewModel
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout

/**
 * ViewBinding的binding是自动生成的，DataBinding的binding是需要xml布局转换后才生成的
 */
class OnSaleFragment : BaseVMFragment<FragmentOnSaleBinding, OnSaleViewModel>() {

    companion object {
        private val TAG: String = "OnSaleFragment"
    }

    protected val mAdapter by lazy {
        OnSaleListAdapter()
    }

    // kotlin 里面可以用 = 代替return
    // 加载的布局页面
    override fun getSubLayoutId(): Int = R.layout.fragment_on_sale

    // 需要观察的VM
    override fun getSubVMClass(): Class<OnSaleViewModel> = OnSaleViewModel::class.java

    override fun initView() {
        super.initView()
        val recyclerView = binding.rv
        recyclerView.run {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect, view: View,
                    parent: RecyclerView, state: RecyclerView.State,
                ) {
                    outRect.bottom = SizeUtils.dp2px(5f)
                }
            })
        }
        val refresh = binding.refreshView
        refresh.run {
            // 开启上滑加载更多
            setEnableLoadmore(true)
            // 开启下拉刷新
            setEnableRefresh(true)
            // 拉动超出有回弹效果
            setEnableOverScroll(true)
            setOnRefreshListener(object : RefreshListenerAdapter() {
                override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                    // 执行上滑加载更多
                }

                override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                    super.onRefresh(refreshLayout)
                    // 执行下拉刷新
                    viewModel.refresh()
                }
            })
        }
    }

    override fun observerData() {
        super.observerData()
        viewModel.loadData()
        // 观察viewModel里面的数据变化
        viewModel.loadState.observe(this, Observer { loadState ->
            LogUtils.i("loadState: $loadState")
            binding.loadState = loadState
        })
        viewModel.contentList.observe(this, Observer { contentList ->
            LogUtils.i("contentList: $contentList")
            mAdapter.setData(contentList)
        })
        viewModel.refreshState.observe(this, Observer {
            if(it == RefreshState.SUCCESS){
                binding.refreshView.finishRefreshing()
            }
        })
    }
}

