package kr.feliz.tutorial_collection.sence.customdialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import kr.feliz.tutorial_collection.databinding.LayoutDialogBinding

class MyCustomDialog(context: Context, myCustomDialogInterface: MyCustomDialogInterface): Dialog(context), View.OnClickListener {

    val TAG: String = "MyCustomDialog"
    private var mBinding : LayoutDialogBinding? = null
    val binding get() = mBinding!!

    private var myCustomDialogInterface: MyCustomDialogInterface? = null

    init {
        this.myCustomDialogInterface = myCustomDialogInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = LayoutDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "MyCustomDialog - onCreate() called")
        // 배경 투명
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.exitBtn.setOnClickListener(this)
        binding.runBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view) {
            binding.exitBtn -> {
                Log.d(TAG, "MyCustomDialog - onClick() called")
                myCustomDialogInterface?.onExitBtnClicked()
            }
            binding.runBtn -> {
                Log.d(TAG, "MyCustomDialog - onClick() called")
                myCustomDialogInterface?.onExitBtnClicked()
            }
        }
    }
}