package kr.feliz.tutorial_collection.sence.lottieanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kr.feliz.tutorial_collection.databinding.ActivityLottieBinding
import kr.feliz.tutorial_collection.sence.callback.CallBackActivity

class LottieActivity : AppCompatActivity() {
    val TAG: String = "LottieActivity"
    private var mBinding : ActivityLottieBinding? = null
    val binding get() = mBinding!!

    lateinit var likeAnimator: ValueAnimator
    lateinit var dislikeAnimator: ValueAnimator
    var isLiked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLottieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isLiked = false

        //binding.likeBtn.playAnimation()
        //ofFloat(시작지점, 종료지점). setDuration(지속시간)
        likeAnimator = ValueAnimator.ofFloat(0f, 0.5f).setDuration(300)
        likeAnimator.addUpdateListener { animation: ValueAnimator ->
            binding.likeBtn.setProgress(
                animation.animatedValue as Float
            )
        }
        likeAnimator.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                val intent = Intent(this@LottieActivity, CallBackActivity::class.java)
                startActivity(intent)
            }
        })

        dislikeAnimator = ValueAnimator.ofFloat(0.5f, 1.0f).setDuration(300)
        dislikeAnimator.addUpdateListener { animation: ValueAnimator ->
            binding.likeBtn.setProgress(
                animation.animatedValue as Float
            )
        }

        binding.likeBtn.setOnClickListener(likeBtnClickListner())
    }

    inner class likeBtnClickListner: View.OnClickListener {
        override fun onClick(v: View?) {
            Log.d(TAG, "onClick: 애니메이션 실행")

            isLiked.let {
                when(it){
                    false -> {
                        Log.d(TAG, "onClick: 좋아요")
                        likeAnimator.start()
                    }
                    true -> {
                        Log.d(TAG, "onClick: 싫어요")
                        dislikeAnimator.start()
                    }
                }
                isLiked = !isLiked
            }
        }
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

}

