package com.example.sampahcapsapp.data.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("message")
    val message: String? = null,

    @SerializedName("Token")
    val token: String? = null,

    @SerializedName("error")
    val error: String? = null
)
