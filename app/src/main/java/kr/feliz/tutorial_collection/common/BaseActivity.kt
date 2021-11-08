package kr.feliz.tutorial_collection.common

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow

abstract class BaseActivity: AppCompatActivity() {

    val TAG: String = "BaseActivity"

    fun changeFragment(frameLayoutId: Int, fragment: Fragment) {
        supportFragmentManager.commitNow(true) {
            //var targetFragment = supportFragmentManager.findFragmentByTag(tagFragmentName)
            var targetFragment = fragment
            val currentFragment = supportFragmentManager.primaryNavigationFragment

            // val bundle = bundleOf("bundleName" to 123)
            if (currentFragment == null) {
                targetFragment = fragment
                add(frameLayoutId,fragment) // add,replace,remove
                Log.d(TAG, " - New fragment added")
            }else{
                hide(currentFragment)
                if (fragment in supportFragmentManager.fragments) {
                    show(targetFragment)
                }else{
                    add(frameLayoutId,fragment)// show/hide(UI숨기거나 보이기), attach/detach(UI계층에서 분리/결합)
                }
            }
            Log.d(TAG, " - reload fragment")

            // addToBackStack(null) // 백스택에 기록
            // setCustomAnimations(enter2, exit2, popEnter2, popExit2)
            setReorderingAllowed(true) // 동시에 commit이 이루어지면 선처리 중이던 commit은 폐기
            setPrimaryNavigationFragment(targetFragment)
        }
    }
}