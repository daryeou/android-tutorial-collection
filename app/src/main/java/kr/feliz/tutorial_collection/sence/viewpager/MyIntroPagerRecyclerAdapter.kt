package kr.feliz.tutorial_collection.sence.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.feliz.tutorial_collection.databinding.LayoutIntroPagerItemBinding

class MyIntroPagerRecyclerAdapter(private var pageList: ArrayList<PageItem>):
    RecyclerView.Adapter<MyPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPagerViewHolder {
        return MyPagerViewHolder(LayoutIntroPagerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyPagerViewHolder, position: Int) {
        holder.bindWithView(pageList[position])
    }

    override fun getItemCount(): Int {
        return pageList.size
    }
}