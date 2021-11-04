package kr.feliz.tutorial_collection.sence.main

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.feliz.tutorial_collection.R
import kr.feliz.tutorial_collection.app.App
import kr.feliz.tutorial_collection.databinding.LayoutRecyclerItemBinding
import kr.feliz.tutorial_collection.sence.main.data.MyModel

class MyViewHolder(binding: LayoutRecyclerItemBinding, recyclerViewInterface: MyRecyclerViewInterface): RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    val TAG: String = "MyViewHolder"

    private val usernameTextView = binding.descriptionTextVeiw
    private val profileImageView = binding.iconImageView
    private var myRecyclerViewInterface: MyRecyclerViewInterface? = null

    init {
        Log.d(TAG, "MyViewHolder - () called")

        itemView.setOnClickListener(this)
        this.myRecyclerViewInterface = recyclerViewInterface
    }

    fun bind(item: MyModel){
        Log.d(TAG, "MyViewHolder - bind() called")
        usernameTextView.text = item.name
        Glide
            .with(App.instance)
            .load(item.profileImage)
            .centerCrop()
            .circleCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(profileImageView)
    }

    override fun onClick(p0: View?) {
        Log.d(TAG, "MyViewHolder - onClick() called")
        this.myRecyclerViewInterface?.onItemClicked(adapterPosition)
    }
}