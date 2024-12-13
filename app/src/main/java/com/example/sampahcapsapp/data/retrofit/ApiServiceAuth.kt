package com.example.sampahcapsapp.data.retrofit

import com.example.sampahcapsapp.data.response.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

interface ApiServiceAuth {
    @POST("auth/login/")
    fun loginUser(@Body request: LoginRequest): Call<AuthResponse>

    @POST("auth/register/")
    fun registerUser(@Body request: RegisterRequest): Call<AuthResponse>
}
