package com.example.wadjet

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.wadjet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    companion object {
        private const val REQUEST_CODE_PERMISSION = 123
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Check and request necessary permissions
        checkAndRequestPermissions()

        // Set click listeners
        binding.riskLevelButton.setOnClickListener { handleRiskLevelButtonClick() }
        binding.logsButton.setOnClickListener { redirectToLogsActivity() }
        binding.sensorsButton.setOnClickListener { redirectToSensorsActivity() }
        binding.securityModeButton.setOnClickListener { redirectToSecurityModeActivity() }

        // Bottom navigation bar listeners
        binding.liveSurveillanceIcon.setOnClickListener { redirectToLiveSurveillanceActivity() }
        binding.sensorsToggleIcon.setOnClickListener { handleSensorsToggleClick() }
        binding.emergencyModeIcon.setOnClickListener { handleEmergencyModeClick() }
    }

    private fun checkAndRequestPermissions() {
        val permissions = arrayOf(android.Manifest.permission.ACCESS_WIFI_STATE, android.Manifest.permission.INTERNET)

        if (!hasPermissions(permissions)) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_PERMISSION)
        }
    }

    private fun hasPermissions(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun handleRiskLevelButtonClick() {
        // Logic for risk level button click
        Toast.makeText(this, "Risk Level Button Clicked", Toast.LENGTH_SHORT).show()
    }

    private fun redirectToLogsActivity() {
        startActivity(Intent(this, LogsActivity::class.java))
    }

    private fun redirectToSensorsActivity() {
        startActivity(Intent(this, SensorActivity::class.java))
    }

    private fun redirectToSecurityModeActivity() {
        // logic for security mode button click
        Toast.makeText(this, "Security Mode Button Clicked", Toast.LENGTH_SHORT).show()
    }

    private fun redirectToLiveSurveillanceActivity() {
        // logic for live surveillance icon click
        Toast.makeText(this, "Live Surveillance Icon Clicked", Toast.LENGTH_SHORT).show()
    }

    private fun handleSensorsToggleClick() {
        //sensors toggle icon click logic
        Toast.makeText(this, "Sensors Toggle Icon Clicked", Toast.LENGTH_SHORT).show()
    }

    private fun handleEmergencyModeClick() {
        //logic for emergency mode icon click
        Toast.makeText(this, "Emergency Mode Icon Clicked", Toast.LENGTH_SHORT).show()
    }
}



