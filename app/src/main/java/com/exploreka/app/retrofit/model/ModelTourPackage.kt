package com.exploreka.app.retrofit.model

import com.google.gson.annotations.SerializedName

data class ModelTourPackage(

	@field:SerializedName("rating_avg_tour_package")
	val ratingAvgTourPackage: Any? = null,

	@field:SerializedName("id_partner")
	val idPartner: Int? = null,

	@field:SerializedName("attraction")
	val attraction: Attraction? = null,

	@field:SerializedName("partner")
	val partner: Partner? = null,

	@field:SerializedName("name_tour_package")
	val nameTourPackage: String? = null,

	@field:SerializedName("start_hour")
	val startHour: String? = null,

	@field:SerializedName("price_tour_package")
	val priceTourPackage: String? = null,

	@field:SerializedName("id_tour_package")
	val idTourPackage: Int? = null,

	@field:SerializedName("end_hour")
	val endHour: String? = null,

	@field:SerializedName("photo_tour_package")
	val photoTourPackage: String? = null,

	@field:SerializedName("desc_tour_package")
	val descTourPackage: String? = null,

	@field:SerializedName("id_attraction")
	val idAttraction: Int? = null
)

data class Partner(

	@field:SerializedName("fullname_partner")
	val fullnamePartner: String? = null
)


