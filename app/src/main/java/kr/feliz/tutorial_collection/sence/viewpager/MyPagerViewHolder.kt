package kr.feliz.tutorial_collection.sence.viewpager

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kr.feliz.tutorial_collection.R
import kr.feliz.tutorial_collection.databinding.LayoutIntroPagerItemBinding

class MyPagerViewHolder(binding: LayoutIntroPagerItemBinding): RecyclerView.ViewHolder(binding.root) {

    private val itemBg: ConstraintLayout = binding.pagerItemBg
    private val itemImage: ImageView = binding.pagerItemIamgeView
    private val itemContent: TextView = binding.pagerItemTextView

    fun bindWithView(pageItem: PageItem) {
        itemImage.setImageResource(pageItem.ImageSrc)
        itemContent.text = pageItem.content

        if(pageItem.bgColor != R.color.red) {
            itemContent.setTextColor(Color.WHITE)
        }

        itemBg.setBackgroundResource(pageItem.bgColor)
    }
}