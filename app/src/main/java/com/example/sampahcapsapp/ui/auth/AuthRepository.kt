package com.example.sampahcapsapp.ui.auth

import com.example.sampahcapsapp.data.retrofit.ApiServiceAuth
import com.example.sampahcapsapp.data.response.AuthResponse
import com.example.sampahcapsapp.data.retrofit.LoginRequest
import com.example.sampahcapsapp.data.retrofit.RegisterRequest
import retrofit2.Call

class AuthRepository(private val apiService: ApiServiceAuth) {

    fun loginUser(email: String, password: String): Call<AuthResponse> {
        return apiService.loginUser(LoginRequest(email, password))
    }

    fun registerUser(name: String, email: String, password: String): Call<AuthResponse> {
        return apiService.registerUser(RegisterRequest(name, email, password))
    }
}
