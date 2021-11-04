package kr.feliz.tutorial_collection.sence.layoutpractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kr.feliz.tutorial_collection.databinding.ActivityLoginBinding

class LayoutPracticeActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var mBinding : ActivityLoginBinding? = null
    val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener{
                _ -> onLoginButtonClicked()
        }
    }

    fun onLoginButtonClicked() {
        Log.d(TAG, "onLoginButtonClicked: ")

        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}