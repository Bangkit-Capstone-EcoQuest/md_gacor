package com.example.sampahcapsapp.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sampahcapsapp.R
import com.example.sampahcapsapp.ui.auth.RegisterActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash_screen)

        val imageView = findViewById<ImageView>(R.id.splash_image)
        Glide.with(this)
            .load(R.drawable.ecoquest_logo) // Pastikan gambar ini berukuran kecil dan beresolusi rendah
            .into(imageView)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) // Delay time in milliseconds (2 seconds)
    }
}
