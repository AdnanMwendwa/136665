package com.example.wadjet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SensorAdapter(private val sensors: List<SensorModel>) :
    RecyclerView.Adapter<SensorAdapter.SensorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sensor, parent, false)
        return SensorViewHolder(view)
    }

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        val sensor = sensors[position]
        holder.bind(sensor)
    }

    override fun getItemCount(): Int {
        return sensors.size
    }

    inner class SensorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sensorIdTextView: TextView = itemView.findViewById(R.id.sensorIdTextView)
        private val roomTextView: TextView = itemView.findViewById(R.id.roomTextView)
        private val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        private val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)

        fun bind(sensor: SensorModel) {
            sensorIdTextView.text = "Sensor ID: ${sensor.id}"
            roomTextView.text = "Room: ${sensor.room}"
            typeTextView.text = "Type: ${sensor.type.name}"
            statusTextView.text = "Status: ${if (sensor.status) "Open" else "Closed"}"
        }
    }
}
