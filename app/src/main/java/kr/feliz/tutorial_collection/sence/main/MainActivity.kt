package kr.feliz.tutorial_collection.sence.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kr.feliz.tutorial_collection.databinding.ActivityMainBinding
import kr.feliz.tutorial_collection.sence.main.data.ClassData
import kr.feliz.tutorial_collection.sence.main.data.MyModel

class MainActivity: AppCompatActivity(), MyRecyclerViewInterface {

    val TAG: String = "MainActivity"
    var modelList = ArrayList<MyModel>()
    private lateinit var myRecyclerAdapter: MyRecyclerAdapter

    var mBinding: ActivityMainBinding? = null
    val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "MainActivity - onCreate() called")
        for (clazz in ClassData.values()){
            var myModel = MyModel(name = clazz.name, profileImage = "https://t1.daumcdn.net/cfile/tistory/24283C3858F778CA2E", clazz.clazz)
            this.modelList.add(myModel)
        }

        myRecyclerAdapter = MyRecyclerAdapter(this@MainActivity)

        myRecyclerAdapter.submitList(this.modelList)
        binding.recylerView.apply {
           layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = myRecyclerAdapter
        }
    }

    override fun onItemClicked(position: Int) {
        Log.d(TAG, "MainActivity - onItemClicked() called")
        Toast.makeText(this, "클릭 이벤트", Toast.LENGTH_SHORT).show()

        var name: String? = null

        AlertDialog.Builder(this).setTitle(title).setMessage("test message")
            .setPositiveButton("Yes"){dialog, id ->
                Log.d(TAG, "MainActivity - onItemClicked() called")
            }.show()

        Log.d(TAG, "MainActivity - onItemClicked() called - 포지션 번호 $position")
        var intent = Intent(this, this.modelList[position].clazz)
        var className: String = intent!!.component!!.className
        Toast.makeText(this, className, Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }
}