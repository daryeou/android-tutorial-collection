package kr.feliz.tutorial_collection.sence.admob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.*
import kr.feliz.tutorial_collection.databinding.ActivityAdmobBinding

class AdmobActivity : AppCompatActivity() {
    val TAG: String = "MainActivity"
    
    private var mBinding : ActivityAdmobBinding? = null
    val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAdmobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MobileAds.initialize(this)

        val adView = binding.adView
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)


        adView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                Log.d(TAG, "MainActivity - onAdLoaded() called")
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                Log.d(TAG, "MainActivity - onAdFailedToLoad() called")
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                Log.d(TAG, "MainActivity - onAdOpened() called")
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                Log.d(TAG, "MainActivity - onAdClicked() called")
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                Log.d(TAG, "MainActivity - onAdClosed() called")
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }
    }
}