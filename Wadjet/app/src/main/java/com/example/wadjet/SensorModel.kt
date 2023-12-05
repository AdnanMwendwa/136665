package com.example.wadjet

data class SensorModel(
    val id: String,
    val room: String,
    val type: SensorType,
    var status: Boolean
)

enum class SensorType {
    DOOR,
    WINDOW
}
