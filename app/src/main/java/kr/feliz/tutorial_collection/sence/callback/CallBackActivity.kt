package kr.feliz.tutorial_collection.sence.callback

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kr.feliz.tutorial_collection.databinding.ActivityCallbackBinding


class CallBackActivity : AppCompatActivity() {

    val TAG: String = "CallBackActivity"
    private var mBinding : ActivityCallbackBinding? = null
    val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCallbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        someCallBackMethod ( completion = {
            Log.d(TAG, "CallBackActivity - $it")
        } )

        Log.d(TAG, "CallBackActivity - onCreate() called")
    }

    fun someCallBackMethod(completion: (String) -> Unit){
        Log.d(TAG, "CallBackActivity - someCallBackMethod() called")

        Handler().postDelayed({
            completion("콜백 완료")
        }, 3000L)
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}