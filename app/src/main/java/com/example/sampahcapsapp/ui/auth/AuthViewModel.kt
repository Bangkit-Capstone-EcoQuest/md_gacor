package com.example.sampahcapsapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampahcapsapp.data.response.AuthResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _authResult = MutableLiveData<Result<AuthResponse>>()
    val authResult: LiveData<Result<AuthResponse>> = _authResult

    fun loginUser(email: String, password: String) {
        repository.loginUser(email, password).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d("AuthViewModel", "Login successful: ${response.body()}")
                    _authResult.value = Result.success(response.body()!!)
                } else {
                    Log.d("AuthViewModel", "Login failed: ${response.errorBody()?.string()}")
                    _authResult.value = Result.failure(Exception("Login failed"))
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("AuthViewModel", "Login error: ${t.message}")
                _authResult.value = Result.failure(t)
            }
        })
    }

    fun registerUser(name: String, email: String, password: String) {
        repository.registerUser(name, email, password).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d("AuthViewModel", "Registration successful: ${response.body()}")
                    _authResult.value = Result.success(response.body()!!)
                } else {
                    Log.d("AuthViewModel", "Registration failed: ${response.errorBody()?.string()}")
                    _authResult.value = Result.failure(Exception("Registration failed"))
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("AuthViewModel", "Registration error: ${t.message}")
                _authResult.value = Result.failure(t)
            }
        })
    }
}
