package com.example.sampahcapsapp.ui.classify

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sampahcapsapp.MainActivity
import com.example.sampahcapsapp.R
import java.io.File

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val imageFilePath = intent.getStringExtra("imageFile")
        val className = intent.getStringExtra("className")
        val confidence = intent.getFloatExtra("confidence", 0f)

        // Log tambahan untuk memeriksa apakah className dan confidence null atau tidak
        Log.d("ResultActivity", "ClassName: $className, Confidence: $confidence")


        val imageView = findViewById<ImageView>(R.id.result_image)
        val resultText = findViewById<TextView>(R.id.result_text)

        if (imageFilePath != null) {
            Glide.with(this)
                .load(File(imageFilePath))
                .into(imageView)
        }

        // Cek apakah className dan confidence tidak null
        if (className != null && confidence != null) {
            resultText.text = "Class: $className\nConfidence: ${"%.2f".format(confidence)}"
        }
        else {
            Log.e("ResultActivity", "ClassName or Confidence is null")
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("navigateTo", "HomeFragment")
        startActivity(intent)
        finish()
        super.onBackPressed()
    }
}