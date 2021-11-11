package kr.feliz.tutorial_collection.sence.home.data

import android.util.Log

class MyModel(var name: String? = null, var profileImage: String? = null, var clazz: Class<out Any>) {
    val TAG: String = "MyModel"
    
    init {
        Log.d(TAG, "MyModel - () called")
    }
}