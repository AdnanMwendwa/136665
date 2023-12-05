package com.example.wadjet

data class LogModel(
    val timestamp: Long,
    val event: String,
    val room: String,
    val sensorId: String,
    val sensorType: SensorType
)
