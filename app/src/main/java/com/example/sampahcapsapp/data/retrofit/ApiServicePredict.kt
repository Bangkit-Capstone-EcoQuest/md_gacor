package com.example.sampahcapsapp.data.retrofit

import com.example.sampahcapsapp.data.response.PredictResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServicePredict {
    @Multipart
    @POST("predict_image")
    fun uploadImage(
        @Part image: MultipartBody.Part
    ): Call<PredictResponse>
}