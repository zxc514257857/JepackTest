package com.example.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewbinding.databinding.RvItemTestBinding

/**
 * viewBinding 在adapter中的使用
 */
class MyRvAdapter(private val list: ArrayList<String>) :
    RecyclerView.Adapter<MyRvAdapter.InnerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        val binding =
            RvItemTestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InnerHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        val innerHolder = holder as InnerHolder
        innerHolder.binding.tvTest.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * 使用viewbinding代替itemView
     */
//    inner class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
    inner class InnerHolder(var binding: RvItemTestBinding) :
        RecyclerView.ViewHolder(binding.root)
}