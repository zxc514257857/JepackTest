package com.example.databinding.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.example.databinding.R
import com.example.databinding.databinding.ItemOnSaleListBinding
import com.example.databinding.domain.OnSaleItem

class OnSaleListAdapter : RecyclerView.Adapter<OnSaleListAdapter.InnerHolder>() {

    private val mContentList by lazy {
        mutableListOf<OnSaleItem>()
    }

    // 这个加载的图片方法可以写在Adapter中，也可以写在单独的一个文件中，这个方法必须是为静态的
    // 比如本类最后几行的这种写法
    companion object {
        @JvmStatic
        // 因为ImageView 这个view不具备加载网络图片的能力，就需要通过绑定适配器的方式去进行扩展
        // 这里填的是图片布局文件对应的命名空间后面的那个字符串
        @BindingAdapter("imageurl", "testcontent")
        // 这个方法名随便取都可以，后面的属性第一个指控件，后面的都是和注解的字段对应的
        fun loadImg(imageView: ImageView, imageUrl: String, testStr: String) {
            LogUtils.e("testStr:$testStr")
            Glide.with(imageView.context).load("https:${imageUrl}_180x180xzq90.jpg_.webp")
                // 加载错误显示图片
                .error(R.mipmap.ic_launcher)
                // 占位图
                .placeholder(R.mipmap.ic_launcher)
                // url为空时显示图片
                .fallback(R.mipmap.ic_launcher)
                .into(imageView)
        }

        // 通过适配器方式 将xml文件中不好写的内容 放到代码中写
        // 数据已经有了，但是格式不对，需要转换一下子
        @SuppressLint("SetTextI18n")
        @JvmStatic
        @BindingAdapter("yuanjia")
        fun dealWithYuanjia(textView: TextView, finalPrice: String) {
            LogUtils.e("string:$finalPrice")
            textView.text = "原价:${String.format("%.2f", finalPrice.toFloat())}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OnSaleListAdapter.InnerHolder {
        // 不能从这里取binding，要从holder中取
        val binding =
            DataBindingUtil.inflate<ItemOnSaleListBinding>(LayoutInflater.from(parent.context),
                R.layout.item_on_sale_list,
                parent,
                false)
        return InnerHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: OnSaleListAdapter.InnerHolder, position: Int) {
        val onSaleItem = mContentList[position]
        // 设置数据
        holder.binding.itemData = onSaleItem
        // 设置布局(中划线)
        holder.binding.tvYuanjia.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
//        // 这两种都是item点击的触发方式
//        holder.itemView.setOnClickListener {
//            LogUtils.e("1111111111111")
//        }
//        holder.binding.root.setOnClickListener {
//            LogUtils.e("2222222222222")
//        }

        // 事件绑定有两种方式：方法绑定和监听器绑定
        // 推荐使用监听器绑定方式，不推荐使用方法绑定方式
        holder.binding.eventHandler = EventHandler(onSaleItem)
    }

    override fun getItemCount(): Int {
        return mContentList.size
    }

    fun setData(contentList: MutableList<OnSaleItem>?) {
        mContentList.clear()
        mContentList.addAll(contentList!!)
        notifyDataSetChanged()
    }

    inner class InnerHolder(itemView: View, val binding: ItemOnSaleListBinding) :
        RecyclerView.ViewHolder(itemView) {}

    // 方法绑定：xml中不能传任何参数过来；只能使用代码中类传参的方式
    inner class EventHandler(private val onSaleItem: OnSaleItem) {
        fun onItemClick(view: View) {
            LogUtils.e("onItemClick::::${onSaleItem.title}")
        }

        fun onItemLongClick(view: View): Boolean {
            LogUtils.e("onItemLongClick::::${onSaleItem.title}")
            return true
        }
    }

    // 监听器绑定：通过点击事件监听的监听器，可以通过xml传递参数过来
    // 类传参以及方法传参都可以
//    inner class EventHandler(private val onSaleItem: OnSaleItem) {
//        fun onItemClick(view: View, itemData: OnSaleItem) {
//            LogUtils.e("aaaaaa::::${itemData.title}")
//            LogUtils.e("aaaaaa::::${onSaleItem.title}")
//        }
//    }
}

//// 这个加载的图片方法可以写在Adapter中，也可以写在单独的一个文件中
//@BindingAdapter("imageurl")
//// 这个方法名随便取都可以
//fun loadImg(imageView: ImageView, imageUrl: String) {
//    Glide.with(imageView.context).load("https:${imageUrl}_180x180xzq90.jpg_.webp")
//        // 加载错误显示图片
//        .error(R.mipmap.ic_launcher)
//        // 占位图
//        .placeholder(R.mipmap.ic_launcher)
//        // url为空时显示图片
//        .fallback(R.mipmap.ic_launcher)
//        .into(imageView)
//}