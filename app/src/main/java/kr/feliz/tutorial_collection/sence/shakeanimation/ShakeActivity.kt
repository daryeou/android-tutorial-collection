package kr.feliz.tutorial_collection.sence.shakeanimation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kr.feliz.tutorial_collection.R
import kr.feliz.tutorial_collection.databinding.ActivityShakeBinding
import kotlin.math.sqrt
import render.animations.*

class ShakeActivity : AppCompatActivity(), SensorEventListener {

    private val TAG = "ShakeActivity"
    private lateinit var sensorManager: SensorManager

    private var mBinding : ActivityShakeBinding? = null
    private val binding get() = mBinding!!

    private var accel: Float = 0.0f
    private var accelCurrent: Float = 0.0f
    private var accelLast: Float = 0.0f
    lateinit var renderAnimation: Render


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityShakeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        renderAnimation = Render(this)

        this.sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accel = 10f
        accelCurrent = SensorManager.GRAVITY_EARTH
        accelLast = SensorManager.GRAVITY_EARTH
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        val event = sensorEvent!!

        val x: Float = event.values.get(0) as Float
        val y: Float = event.values.get(1) as Float
        val z: Float = event.values.get(2) as Float

        // Log.d(TAG, "MainActivity test - $x, $y, $z")
        binding.xTextView.text = getString(R.string.xText,x)
        binding.yTextView.text = getString(R.string.xText,y)
        binding.zTextView.text = getString(R.string.xText,z)

        accelLast = accelCurrent

        accelCurrent = sqrt((x * x + y * y + z * z).toDouble()).toFloat()

        val delta: Float = accelCurrent - accelLast

        accel = accel * 0.9f + delta
        // Log.d(TAG, "onSensorChanged: $accel $accelCurrent $accelLast")

        if (accel > 3) {
            Log.d(TAG, "MainActivity - accel : $accel")
            Log.d(TAG, "MainActivity - Shake it")

            binding.faceImageView.setImageResource(R.drawable.ic_face_smile)

            renderAnimation.setAnimation(Attention().Wobble(binding.faceImageView))
            renderAnimation.start()

            Handler().postDelayed({
                binding.faceImageView.setImageResource(R.drawable.ic_face)
            }, 1000)
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.d(TAG, "MainActivity - onAccuracyChanged() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MainActivity - onResume() called")
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        Log.d(TAG, "MainActivity - onPause() called")
        sensorManager.unregisterListener(this)
        super.onPause()
    }
}