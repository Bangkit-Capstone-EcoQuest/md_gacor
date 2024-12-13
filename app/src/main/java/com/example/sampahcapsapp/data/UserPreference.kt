package com.example.sampahcapsapp.data

import android.content.Context
import android.content.SharedPreferences

class UserPreference(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveAuthToken(token: String) {
        preferences.edit().putString("auth_token", token).apply()
    }

    fun getAuthToken(): String? {
        return preferences.getString("auth_token", null)
    }

    fun clearAuthToken() {
        preferences.edit().remove("auth_token").apply()
    }

    // Metode untuk menyimpan poin
    fun savePoints(points: Int) {
        preferences.edit().putInt("user_points", points).apply()
    }

    // Metode untuk mengambil poin
    fun getPoints(): Int {
        return preferences.getInt("user_points", 0) // default value is 0
    }

    // Metode untuk menghapus poin
    fun clearPoints() {
        preferences.edit().remove("user_points").apply()
    }
}
