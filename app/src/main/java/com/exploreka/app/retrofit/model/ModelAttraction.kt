package com.exploreka.app.retrofit.model

import com.google.gson.annotations.SerializedName

data class ModelAttraction(

	@field:SerializedName("desc_attraction")
	val descAttraction: String? = null,

	@field:SerializedName("photo_attraction")
	val photoAttraction: Any? = null,

	@field:SerializedName("city")
	val city: City? = null,

	@field:SerializedName("attraction_category")
	val attractionCategory: AttractionCategory? = null,

	@field:SerializedName("price_attraction")
	val priceAttraction: String? = null,

	@field:SerializedName("close_hour")
	val closeHour: String? = null,

	@field:SerializedName("id_city")
	val idCity: Int? = null,

	@field:SerializedName("name_attraction")
	val nameAttraction: String? = null,

	@field:SerializedName("rating_avg_attraction")
	val ratingAvgAttraction: Any? = null,

	@field:SerializedName("id_attraction_cat")
	val idAttractionCat: Int? = null,

	@field:SerializedName("open_hour")
	val openHour: String? = null,

	@field:SerializedName("id_attraction")
	val idAttraction: Int? = null,

	@field:SerializedName("coordinate_attraction")
	val coordinateAttraction: String? = null
)

data class CoordinateAttraction(
	@field:SerializedName("lat")
	val latitude: Double,

	@field:SerializedName("lng")
	val longitude: Double
)



data class City(

	@field:SerializedName("id_province")
	val idProvince: Int? = null,

	@field:SerializedName("id_city")
	val idCity: Int? = null,

	@field:SerializedName("name_city")
	val nameCity: String? = null
)

data class AttractionCategory(

	@field:SerializedName("id_attraction_cat")
	val idAttractionCat: Int? = null,

	@field:SerializedName("name_attraction_cat")
	val nameAttractionCat: String? = null
)
