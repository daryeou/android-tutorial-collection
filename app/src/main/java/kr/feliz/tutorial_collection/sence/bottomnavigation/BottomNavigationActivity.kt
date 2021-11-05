package kr.feliz.tutorial_collection.sence.bottomnavigation

import android.os.Bundle
import android.util.Log
import com.google.android.material.navigation.NavigationBarView
import kr.feliz.tutorial_collection.R
import kr.feliz.tutorial_collection.common.BaseActivity
import kr.feliz.tutorial_collection.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity: BaseActivity() {
    private lateinit var homeFragment: HomeFragment
    private lateinit var rankingFragment: RankingFragment
    private lateinit var profileFragment: ProfileFragment

    override val TAG: String = "BottomNavActivity"

    private var mBinding : ActivityBottomNavigationBinding? = null
    val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        homeFragment = HomeFragment.newInstance()
        rankingFragment = RankingFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()

        changeFragment(binding.fragmentFrame.id, homeFragment)
        binding.bottomNav.setOnItemSelectedListener(onBottomNavItemSelectedListener)
    }

    private var onBottomNavItemSelectedListener = NavigationBarView.OnItemSelectedListener {
        Log.d(TAG, "BottomNavigationActivity - onCreate() called")

        when(it.itemId) {
            R.id.menuHome -> {
                Log.d(TAG, "BottomNavigationActivity - Home clicked")
                changeFragment(binding.fragmentFrame.id, homeFragment)
            }
            R.id.menuRanking -> {
                Log.d(TAG, "BottomNavigationActivity - Ranking clicked")
                changeFragment(binding.fragmentFrame.id, rankingFragment)
            }
            R.id.menuProfile -> {
                Log.d(TAG, "BottomNavigationActivity - Profile clicked")
                changeFragment(binding.fragmentFrame.id, profileFragment)
            }
        }
        true
    }

}