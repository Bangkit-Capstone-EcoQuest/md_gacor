package com.example.sampahcapsapp.data.response

import com.google.gson.annotations.SerializedName

data class PredictResponse(
	@SerializedName("Jenis Plastik")
	val predicted_class: String,

	@SerializedName("Confidence")
	val confidence: Float,
)
