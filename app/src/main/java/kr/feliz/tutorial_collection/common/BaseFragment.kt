package kr.feliz.tutorial_collection.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kr.feliz.tutorial_collection.utils.autoCleared

// TODO: autoClearedVaule를 사용하여 바인딩 할 것
abstract class BaseFragment<T: ViewDataBinding>(): Fragment() {
    private var binding by autoCleared<T>()

    abstract val resId: Int

//    private var _binding: T? = null
//    private val mBinding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, resId, container, false)
        return binding.root
    }


    fun getViewBinding(): T{
        return binding
    }

    override fun onDestroyView() {
        // _binding = null
        super.onDestroyView()
    }
}

//class User(val map: Map<String,Any?>){
//    val name: String by map
//    val age: Int by map
//}
//
//val user = User(mapOf(
//    "name" to "john",
//    "age" to 25
//))
//
//fun test() {
//    println(user.name)
//}