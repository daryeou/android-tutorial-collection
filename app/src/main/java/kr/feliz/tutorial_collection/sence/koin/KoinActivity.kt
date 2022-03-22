package kr.feliz.tutorial_collection.sence.koin

import android.os.Bundle
import android.util.Log
import kr.feliz.tutorial_collection.common.BaseActivity
import kr.feliz.tutorial_collection.databinding.ActivityKoinBinding
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class KoinActivity: BaseActivity() {
    companion object {
        const val TAG: String = "KoinActivity"
    }
    private var mBinding : ActivityKoinBinding? = null
    val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityKoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Koin 예제 1 */
        val student : Student by inject()
        val teacherCho : Teacher by inject(named("cho"))
        val teacherTan : Teacher by inject(named("tan"))

        Log.d(TAG, "onCreate: ${student.name} ${teacherCho.name}")

        binding.teacherTextView.text = teacherCho.name +"/"+ teacherTan.name
        binding.studentTextView.text = student.name

        val studentSecond : Student by inject(named("sec"))
        val teacherSecond : Teacher by inject(named("sec"))

        binding.teacherSecondTextView.text = teacherSecond.name
        binding.studentSecondTextView.text = studentSecond.name

        val teacherFactory : Teacher by inject(named("factory"))
        binding.studentFactoryTextView.text = teacherFactory.name
    }

}