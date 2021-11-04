package kr.feliz.tutorial_collection.app

import android.app.Application

class App : Application(){
    companion object{
        lateinit var instance: App
            private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
//        val handler = ApplicationLifecycleHandler()
//        registerActivityLifecycleCallbacks(handler)
//        registerComponentCallbacks(handler)
    }
}