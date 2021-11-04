package kr.feliz.tutorial_collection.sence.customdialog

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kr.feliz.tutorial_collection.databinding.ActivityDialogBinding

class DialogActivity : AppCompatActivity(), MyCustomDialogInterface {

    val TAG: String = "DialogActivity"
    private var mBinding : ActivityDialogBinding? = null
    val binding get() = mBinding!!

    private lateinit var myCustomDialog: MyCustomDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "DialogActivity - onCreate() called")
        mBinding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // 재부팅 할 때 사용 : https://stackoverflow.com/questions/40424394/what-is-the-usage-of-oncreate-method-second-implementation-in-android-activities
    // persistentState는 onPause 다음에 호출 가능 : https://jungwoon.github.io/android/2019/07/15/Activity.html
    // bundle 과 persistableBundle의 차이점 : https://stackoverflow.com/questions/48866991/bundle-vs-persistablebundle-in-android
   override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    fun onDialogBtnClicked(view: View){
        Log.d(TAG, "DialogActivity - onDialogBtnClicked() called")
        myCustomDialog = MyCustomDialog(this, this)
        myCustomDialog.show()
    }

    override fun onExitBtnClicked() {
        Log.d(TAG, "DialogActivity - onExitBtnClicked() called")
        Toast.makeText(this, "나가기", Toast.LENGTH_SHORT).show()
        myCustomDialog.dismiss()
    }

    override fun onRunBtnClicked() {
        Log.d(TAG, "DialogActivity - onRunBtnClicked() called")
        Toast.makeText(this, "실행", Toast.LENGTH_SHORT).show()
        myCustomDialog.dismiss()
    }
}