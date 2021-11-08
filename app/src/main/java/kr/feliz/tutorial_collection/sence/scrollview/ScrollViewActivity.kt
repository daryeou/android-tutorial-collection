package kr.feliz.tutorial_collection.sence.scrollview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import kr.feliz.tutorial_collection.R
import kr.feliz.tutorial_collection.databinding.ActivityScrollviewBinding
import kr.feliz.tutorial_collection.sence.scrollview.utils.Constants
import kr.feliz.tutorial_collection.sence.scrollview.utils.SEARCH_TYPE

class ScrollViewActivity : AppCompatActivity() {

    val TAG: String = "ScrollViewActivity"

    private var mBinding : ActivityScrollviewBinding? = null
    private val binding get() = mBinding!!

    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityScrollviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(Constants.TAG, "ScrollViewActivity - onCreate() called")

        // 라디오 그룹 가져오기
        binding.searchTermRadioGroup.setOnCheckedChangeListener {_, checkedId->
            when(checkedId) {
                R.id.searchPhotoRadioBtn -> {
                    Log.d(TAG, "사진 검색버튼 클릭")
                    binding.searchTermTextLayout.hint = "사진 검색"
                    binding.searchTermTextLayout.startIconDrawable = ResourcesCompat.getDrawable(resources,R.drawable.ic_photo,resources.newTheme())
                }
                R.id.searchUserRadioBtn -> {
                    Log.d(TAG, "사용자 검버튼 클릭")
                    binding.searchTermTextLayout.hint = "사용자 검색"
                    binding.searchTermTextLayout.startIconDrawable = ResourcesCompat.getDrawable(resources,R.drawable.ic_person,resources.newTheme())
                }
            }
        }
    }
}