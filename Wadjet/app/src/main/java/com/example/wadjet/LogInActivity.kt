package com.example.wadjet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

import java.io.DataOutputStream
import java.io.IOException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

public class LogInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()


        loginButton.setOnClickListener
            val email = findViewById<emailEditText>(R.id.emailEditText).text.toString()
            val password = findViewById<passwordEditText>(R.id.passwordEditText).text.toString()

            // Input validation
            if (email.isEmpty() || password.isEmpty()) {
                showToast("Please enter both email and password")
            } else {
                //method to perform login
                initiateLogin(email)
            }
        }

        registerButton.setOnClickListener {
            //logic to get email and password from EditText fields
            val email = findViewById<emailEditText>(R.id.emailEditText).text.toString()
            val password = findViewById<passwordEditText>(R.id.passwordEditText).text.toString()

            // Input validation
            if (email.isEmpty() || password.isEmpty()) {
                showToast("Please enter both email and password")
            } else {
                //method to register the user
                initiateRegistration(email, password)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initiateLogin(email: String) {
        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val signInMethods = task.result?.signInMethods
                if (signInMethods != null && signInMethods.contains("password")) {
                    // User has email/password authentication enabled, proceed with regular login
                    login(email, findViewById<passwordEditText>(R.id.passwordEditText).text.toString())
                } else {
                    showToast("No authentication methods found for this email")
                }
            } else {
                showToast("Error checking authentication methods")
            }
        }
    }

    private fun initiateRegistration(email: String, password: String) {
        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val signInMethods = task.result?.signInMethods
                if (signInMethods != null && signInMethods.contains("password")) {
                    showToast("Email already registered")
                } else {
                    // No email/password authentication, proceed with regular registration
                    registerUser(email, password)
                }
            } else {
                showToast("Error checking authentication methods")
            }
        }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Successfully logged in
                    showToast("Login successful")

                } else {

                    showToast("Login failed: ${task.exception?.message}")
                }
            }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Successfully registered
                    showToast("Registration successful")

                    // Get the current user UID
                    val uid = auth.currentUser?.uid


                    if (uid != null) {
                        storeUserDetailsInBackend(uid, email)
                    }
                } else {

                    showToast("Registration failed: ${task.exception?.message}")
                }
            }
    }

    private fun storeUserDetailsInBackend(uid: String, email: String) {
        val backendUrl = "https://console.firebase.google.com/project/wadjet-370023/database/wadjet-370023-default-rtdb/data/~2F"


        val url = URL("$backendUrl/users")
        val connection = (url.openConnection() as HttpsURLConnection).apply {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json")
            doOutput = true
        }

        return try {
            // JSON payload for user details
            val jsonInputString = "{\"uid\": \"$uid\", \"email\": \"$email\"}"


            val os = DataOutputStream(connection.outputStream)
            os.writeBytes(jsonInputString)
            os.flush()
            os.close()

            // Get response from the server
            val responseCode = connection.responseCode
            if (responseCode == HttpsURLConnection.HTTP_OK) {

            } else {

        } catch (e: IOException) {
            e.printStackTrace()

        } finally {
            connection.disconnect()
        }
    }
}

