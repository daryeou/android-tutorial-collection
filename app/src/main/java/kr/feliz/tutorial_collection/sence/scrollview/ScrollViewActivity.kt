package kr.feliz.tutorial_collection.sence.scrollview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.feliz.tutorial_collection.databinding.ActivityScrollviewBinding

class ScrollViewActivity : AppCompatActivity() {

    val TAG: String = "ScrollViewActivity"

    private var mBinding : ActivityScrollviewBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityScrollviewBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}