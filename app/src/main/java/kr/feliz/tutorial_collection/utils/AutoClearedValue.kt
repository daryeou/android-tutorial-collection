package kr.feliz.tutorial_collection.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.lang.IllegalStateException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AutoClearedValue<T: Any>(private val fragment: Fragment): ReadWriteProperty<Fragment, T> {
    private var _value: T? = null

    init{
        // fragment's lifeCycle
        fragment.lifecycle.addObserver(ObserverFragmentOnCreate())
    }

    inner class ObserverFragmentOnCreate(): DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            // view's owner lifeCycle
            fragment.viewLifecycleOwnerLiveData.observe(fragment) {
                    viewLifeCycleOwner -> viewLifeCycleOwner.lifecycle.addObserver(ObserverViewOwnerOnDestroy())
            }
        }
    }

    class User(val map: Map<String, Any?>) {
        val name: String by map
        val age: Int     by map
    }

    inner class ObserverViewOwnerOnDestroy(): DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            _value = null
        }
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException("Auto-cleared-value, It not be available")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        _value = value
    }
}

// 확장함수이므로 Fragment를 상속받는 클래스에서 쓸 수 있음.
fun <T: Any> Fragment.autoCleared() = AutoClearedValue<T>(this)