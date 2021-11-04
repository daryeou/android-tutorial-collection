package kr.feliz.tutorial_collection.sence.layoutpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.feliz.tutorial_collection.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val TAG = "SecondActivity"
    private var mBinding : ActivitySecondBinding? = null
    val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.backBtn.setOnClickListener{
//                view -> onBackButtonClicked(view)
//        }
    }

//    fun onBackButtonClicked(view: View) {
//        Log.d(TAG, "onBackButtonClicked: ")
//        finish()
//    }
}