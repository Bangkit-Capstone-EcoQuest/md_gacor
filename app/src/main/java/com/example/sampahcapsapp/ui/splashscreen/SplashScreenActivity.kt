package com.example.sampahcapsapp.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sampahcapsapp.R
import com.example.sampahcapsapp.ui.auth.RegisterActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        // Set the content view to the splash layout
        setContentView(R.layout.activity_splash_screen)  // Ensure you have created this layout file

        // Delay for 2 seconds before starting the main activity
        Handler(Looper.getMainLooper()).postDelayed({
            // Create an intent to start the MainActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent) // Start the MainActivity
            finish() // Finish the SplashActivity so the user cannot return to it
        }, 500) // Delay time in milliseconds
    }
}