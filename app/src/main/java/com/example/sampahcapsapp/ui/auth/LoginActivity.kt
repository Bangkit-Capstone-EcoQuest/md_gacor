package com.example.sampahcapsapp.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sampahcapsapp.MainActivity
import com.example.sampahcapsapp.ui.auth.AuthRepository
import com.example.sampahcapsapp.data.UserPreference
import com.example.sampahcapsapp.data.retrofit.ApiConfigAuth
import com.example.sampahcapsapp.databinding.ActivityLoginBinding
import com.example.sampahcapsapp.ui.AuthViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userPreference: UserPreference

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(AuthRepository(ApiConfigAuth.getApiServiceAuth()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreference = UserPreference(this)

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT).show()
            } else {
                authViewModel.loginUser(email, password)
            }
        }

        binding.registerTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        authViewModel.authResult.observe(this, { result ->
            result.fold(
                onSuccess = { authResponse ->
                    if (authResponse != null) {
                        authResponse.token?.let { userPreference.saveAuthToken(it) }
                        Toast.makeText(this, authResponse.message, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                },
                onFailure = {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            )
        })
    }
}
