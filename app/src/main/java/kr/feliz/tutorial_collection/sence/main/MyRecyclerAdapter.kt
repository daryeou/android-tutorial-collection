package kr.feliz.tutorial_collection.sence.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.feliz.tutorial_collection.databinding.LayoutRecyclerItemBinding
import kr.feliz.tutorial_collection.sence.main.data.MyModel

class MyRecyclerAdapter(myRecyclerViewInterface: MyRecyclerViewInterface): RecyclerView.Adapter<MyViewHolder>() {

    val TAG: String = "MyRecyclerAdapter"

    var modelList: List<MyModel> = ArrayList<MyModel>()

    private var myRecyclerViewInterface: MyRecyclerViewInterface? = null

    init {
        this.myRecyclerViewInterface = myRecyclerViewInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view,this.myRecyclerViewInterface!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, "MyRecyclerAdapter - onBindViewHolder() called")
        holder.bind(modelList[position])
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    fun submitList(modelList: ArrayList<MyModel>){
        this.modelList = modelList
    }
}