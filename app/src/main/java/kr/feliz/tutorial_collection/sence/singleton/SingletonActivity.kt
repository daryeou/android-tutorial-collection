package kr.feliz.tutorial_collection.sence.singleton

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kr.feliz.tutorial_collection.databinding.ActivitySingletonBinding

class SingletonActivity: AppCompatActivity() {
    val TAG: String = "SingletonActivity"
    private var mBinding : ActivitySingletonBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySingletonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "SingletonActivity - onCreate() called")

        val mySQLOpenHelper1 = MySQLOpenHelperSingleton.getInstance(this)
        val mySQLOpenHelper2 = MySQLOpenHelperSingleton.getInstance(this)
        Toast.makeText(this, "1: $mySQLOpenHelper1 2: $mySQLOpenHelper2", Toast.LENGTH_SHORT).show()
        binding.sqlResultTextView.text = "1: $mySQLOpenHelper1 2: $mySQLOpenHelper2"
    }
}