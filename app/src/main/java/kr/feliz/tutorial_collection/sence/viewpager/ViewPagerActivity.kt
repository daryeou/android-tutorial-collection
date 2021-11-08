package kr.feliz.tutorial_collection.sence.viewpager

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import kr.feliz.tutorial_collection.R
import kr.feliz.tutorial_collection.common.BaseActivity
import kr.feliz.tutorial_collection.databinding.ActivityPagerBinding
import kr.feliz.tutorial_collection.databinding.ActivitySingletonBinding

class ViewPagerActivity: BaseActivity() {

    companion object {
        const val TAG: String = "ViewPagerActivity"
    }

    private var mBinding : ActivityPagerBinding? = null
    private val binding get() = mBinding!!

    private var pageItemList = ArrayList<PageItem>()
    private lateinit var myIntroPagerRecyclerAdapter: MyIntroPagerRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "ViewPagerActivity - onCreate() called")

        // 데이터 배열 준비
        pageItemList.add(PageItem(R.color.blue,R.drawable.image_chart,"간편해진 차트보기"))
        pageItemList.add(PageItem(R.color.red,R.drawable.image_chart,"간편해진 차트보기"))
        pageItemList.add(PageItem(R.color.blue,R.drawable.image_chart,"간편해진 차트보기"))

        myIntroPagerRecyclerAdapter = MyIntroPagerRecyclerAdapter(pageItemList)
        binding.myIntroViewPager.apply {
            adapter = myIntroPagerRecyclerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            binding.dotsIndicator.setViewPager2(this)
        }

        binding.previousBtn.setOnClickListener{
            Log.d(TAG, "ViewPagerActivity - previous button clicked")
            binding.myIntroViewPager.currentItem -= 1
        }
        binding.nextBtn.setOnClickListener{
            Log.d(TAG, "ViewPagerActivity - next button clicked")
            binding.myIntroViewPager.currentItem += 1
        }
    }
}