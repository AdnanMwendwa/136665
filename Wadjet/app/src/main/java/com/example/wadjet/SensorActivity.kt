package com.example.wadjet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sensor.*

 class SensorActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        // Initialize Firebase Realtime Database
        database = FirebaseDatabase.getInstance()

        // Manually generate virtual sensors
        val virtualSensors = generateVirtualSensors()

        // Simulate functioning of sensors
        simulateSensorFunctionality(virtualSensors)

        val sensorAdapter = SensorAdapter(virtualSensors)
        sensorRecyclerView.adapter = sensorAdapter

        // Log sensor data to Firebase Realtime Database
        logSensorData(virtualSensors)
    }

    private fun generateVirtualSensors(): List<SensorModel> {
        // Generate virtual sensors with unique IDs and types
        val sensors = mutableListOf<SensorModel>()

        for (i in 1..5) {
            val sensorId = "Sensor_$i"
            val room = "Room_${i % 3 + 1}"
            val sensorType = if (i % 2 == 0) SensorType.DOOR else SensorType.WINDOW

            val sensor = SensorModel(sensorId, room, sensorType, false)
            sensors.add(sensor)
        }

        return sensors
    }

    private fun simulateSensorFunctionality(sensors: List<SensorModel>) {
        // Simulation of functioning of sensors (open/close randomly)
        sensors.forEach { sensor ->
            simulateSensorActions(sensor)
        }
    }

    private fun simulateSensorActions(sensor: SensorModel) {
        // Simulation of sensor actions (open/close) with random data
        val random = java.util.Random()

        //update sensor status
        val updateInterval = 5000L // 5 seconds

        val updateRunnable = object : Runnable {
            override fun run() {
                sensor.status = random.nextBoolean()
                simulateSensorActions(sensor) // Schedule the next update
            }
        }

        // Run the initial update
        updateRunnable.run()

        // Schedule periodic updates
        sensorRecyclerView.postDelayed(updateRunnable, updateInterval)
    }

    private fun logSensorData(sensors: List<SensorModel>) {
        // Log sensor data to Firebase Realtime Database
        val sensorsRef = database.reference.child("sensors")

        sensors.forEach { sensor ->
            sensorsRef.child(sensor.id).setValue(sensor)
        }
    }
}
