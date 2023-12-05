package com.example.wadjet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class LogsActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var logsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs)

        // Initialize Firebase Realtime Database
        database = FirebaseDatabase.getInstance()

        // Initialize RecyclerView
        logsRecyclerView = findViewById(R.id.logsRecyclerView)
        logsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch and display logs
        fetchAndDisplayLogs()
    }

    private fun fetchAndDisplayLogs() {
        val logsRef = database.reference.child("logs")

        logsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val logs = mutableListOf<LogModel>()

                for (logSnapshot in dataSnapshot.children) {
                    val log = logSnapshot.getValue(LogModel::class.java)
                    log?.let { logs.add(it) }
                }

                displayLogs(logs)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })
    }

    private fun displayLogs(logs: List<LogModel>) {
        val logAdapter = LogAdapter(logs)
        logsRecyclerView.adapter = logAdapter
    }
}
