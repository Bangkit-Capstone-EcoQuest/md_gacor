package com.example.sampahcapsapp.data.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfigPredict {
    private const val BASE_URL = "https://ml-ecoquest-21090267695.asia-southeast2.run.app/"


    fun getApiServicePredict(): ApiServicePredict {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Log semua request dan response body
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // Menambahkan interceptor untuk log
            .connectTimeout(30, TimeUnit.SECONDS) // Waktu timeout koneksi
            .readTimeout(30, TimeUnit.SECONDS)    // Waktu timeout pembacaan respons
            .writeTimeout(30, TimeUnit.SECONDS)   // Waktu timeout penulisan permintaan
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServicePredict::class.java)
    }
}