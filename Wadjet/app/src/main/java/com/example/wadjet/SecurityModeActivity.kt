package com.example.wadjet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast

class SecurityModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_mode)

        val btnArm: Button = findViewById(R.id.btnArm)
        val btnDisarm: Button = findViewById(R.id.btnDisarm)

        btnArm.setOnClickListener {
            // Handle arming the security system
            armSecurity()
        }

        btnDisarm.setOnClickListener {
            // Handle disarming the security system
            disarmSecurity()
        }
    }

    fun armSecurity(view: View) {
        //logic to arm the security system using IoT devices
        Toast.makeText(this, "Security System Armed", Toast.LENGTH_SHORT).show()
    }

    fun disarmSecurity(view: View) {
        //logic to disarm the security system using IoT devices
        Toast.makeText(this, "Security System Disarmed", Toast.LENGTH_SHORT).show()
    }
}
