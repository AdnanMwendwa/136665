package com.example.wadjet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LogAdapter(private val logs: List<LogModel>) :
    RecyclerView.Adapter<LogAdapter.LogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_log, parent, false)
        return LogViewHolder(view)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        val log = logs[position]
        holder.bind(log)
    }

    override fun getItemCount(): Int {
        return logs.size
    }

    inner class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timestampTextView: TextView = itemView.findViewById(R.id.timestampTextView)
        private val eventTextView: TextView = itemView.findViewById(R.id.eventTextView)
        private val roomTextView: TextView = itemView.findViewById(R.id.roomTextView)
        private val sensorIdTextView: TextView = itemView.findViewById(R.id.sensorIdTextView)
        private val sensorTypeTextView: TextView = itemView.findViewById(R.id.sensorTypeTextView)

        fun bind(log: LogModel) {
            timestampTextView.text = "Timestamp: ${log.timestamp}"
            eventTextView.text = "Event: ${log.event}"
            roomTextView.text = "Room: ${log.room}"
            sensorIdTextView.text = "Sensor ID: ${log.sensorId}"
            sensorTypeTextView.text = "Sensor Type: ${log.sensorType.name}"
        }
    }
}
