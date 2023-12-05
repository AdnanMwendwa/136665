package com.example.wadjet

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.VideoView

class LiveSurveillanceActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_surveillance)

        videoView = findViewById(R.id.videoView) // Replace with your VideoView's ID

        // Placeholder: Simulate live surveillance feed
        val videoUrl = "https://your-iott-camera-url" // Replace with the actual video stream URL

        videoView.setVideoPath(videoUrl)
        videoView.start()

        // Continuously check for live feed updates
        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                // Update the live feed though access to sensors and microcontroller units was limited
                // Use a video streaming library or API to handle live feed from IoT devices.
                handler.postDelayed(this, 1000) // Delay for live updates
            }
        })
    }
}
