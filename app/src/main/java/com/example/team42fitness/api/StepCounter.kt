package com.example.team42fitness.api

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlin.math.sqrt

class StepCounter (private val activity: Activity) : SensorEventListener {
    private val TAG = "StepCounter"

    private var sensorManager = activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private var stepCount = 0

    private val accDataLength = 10
    private var accData = mutableListOf<Pair<Long, Float>>()

    private var lastSampleTime : Long? = null

    // Starts recording steps
    fun startRecording() {
        if (accSensor != null) {
            stepCount = 0
            sensorManager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        else {
            Log.e(TAG, "Acceleration sensor is null!")
        }
    }

    // Stops recording steps and returns step count
    fun stopRecording() : Int {
        sensorManager.unregisterListener(this)
        lastSampleTime = null
        accData.clear()
        return stepCount
    }

    fun getStepCount() : Int {
        return stepCount
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            if (lastSampleTime == null) {
                lastSampleTime = event.timestamp
                return
            }

            val timeElapsed = (event.timestamp - lastSampleTime!!) / 1e9

            // Record acceleration at minimum every 0.1 seconds (10 Hz)
            if (timeElapsed >= 0.1) {
                val accX = event.values[0]
                val accY = event.values[1]
                val accZ = event.values[2]
                var acc = sqrt(accX * accX + accY * accY + accZ * accZ)
                acc -= 9.80665f // subtract out gravity

                accData.add(Pair(event.timestamp, acc))
                if (accData.count() > accDataLength) {
                    accData.removeFirst()
                }

                calculateStep()
                lastSampleTime = event.timestamp
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    // Simple hacky algorithm to count steps
    private fun calculateStep() {
        if (accData.count() < accDataLength) return

        var minVal = Pair(0L, Float.MAX_VALUE)
        var maxVal = Pair(0L, Float.MIN_VALUE)

        for (x in accData) {
            if (x.second < minVal.second) {
                minVal = x
            }
            if (x.second > maxVal.second) {
                maxVal = x
            }
        }

        if (maxVal.second - minVal.second > 3f) {
            if (maxVal.first > minVal.first) {
                stepCount++
                accData.clear()
            }
        }
    }
}