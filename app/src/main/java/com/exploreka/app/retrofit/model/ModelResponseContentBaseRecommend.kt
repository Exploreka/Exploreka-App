package com.exploreka.app.retrofit.model

import com.google.gson.annotations.SerializedName

data class ModelResponseContentBaseRecommend(

	@field:SerializedName("recommended_places")
	val recommendedPlaces: List<List<String?>?>? = null
)
