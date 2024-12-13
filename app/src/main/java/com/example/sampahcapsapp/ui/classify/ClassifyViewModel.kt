package com.example.sampahcapsapp.ui.classify

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampahcapsapp.data.retrofit.ApiConfigPredict
import com.example.sampahcapsapp.data.response.PredictResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

class ClassifyViewModel : ViewModel() {

    private val _predictResult = MutableLiveData<PredictResponse>()
    val predictResult: LiveData<PredictResponse> = _predictResult

    fun uploadImage(file: File) {
        // Tentukan tipe file berdasarkan ekstensi file
        val fileExtension = file.extension
        val mimeType = when (fileExtension) {
            "jpg", "jpeg" -> "image/jpeg"
            "png" -> "image/png"
            else -> "image/*"
        }

        val requestImageFile = file.asRequestBody(mimeType.toMediaTypeOrNull())
        val imageMultipart = MultipartBody.Part.createFormData(
            "file", file.name, requestImageFile
        )

        val client = ApiConfigPredict.getApiServicePredict().uploadImage(imageMultipart)
        client.enqueue(object : Callback<PredictResponse> {
            override fun onResponse(
                call: Call<PredictResponse>,
                response: Response<PredictResponse>
            ) {
                if (response.isSuccessful) {
                    _predictResult.value = response.body()
                    Log.d("ClassifyViewModel", "Response Successful: ${response.body()}") // Log respons sukses
                    Log.d("ClassifyViewModel", "Predicted Class: ${response.body()?.predicted_class}, Confidence: ${response.body()?.confidence}") // Log prediksi dan confidence
                } else {
                    Log.e("ClassifyViewModel", "onResponse: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                Log.e("ClassifyViewModel", "onFailure: ${t.message}", t)
            }
        })
    }

    fun resizeImage(file: File): File {
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true)
        val resizedFile = File(file.parent, "resized_${file.name}")
        val outputStream = FileOutputStream(resizedFile)
        resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        return resizedFile
    }
}
