package com.zhr.mvvm.taobao

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhr.mvvm.R
import com.zhr.mvvm.domain.MapData
import kotlinx.android.synthetic.main.item_onsale.view.*

class OnSaleListAdapter : RecyclerView.Adapter<OnSaleListAdapter.InnerHolder>() {

    private val contentList = arrayListOf<MapData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_onsale, parent, false)
        return InnerHolder(itemView)
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        val mapData = contentList[position]
        holder.itemView.run {
            tvTitle.text = mapData.title
            // 这里会容易出现精度缺失问题，所以要保留两位小数
            tvPrice2.text = String.format("￥%.2f", mapData.zkFinalPrice.toFloat() - mapData.couponAmount)
            Glide.with(context).load("https://${mapData.pictUrl}").into(ivPic)
        }
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    fun setData(contentList: List<MapData>) {
        this.contentList.clear()
        this.contentList.addAll(contentList)
        notifyDataSetChanged()
    }

    inner class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 这里应该不需要findViewById 应该用kotlin的特性能够在 onBindViewHolder里面找到Id
    }
}