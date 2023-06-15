package com.exploreka.app.retrofit.model

import com.google.gson.annotations.SerializedName

data class Modelresponsecontentbaserecommend(

	@field:SerializedName("recommended_places")
	val recommendedPlaces: List<List<String?>?>? = null
)
