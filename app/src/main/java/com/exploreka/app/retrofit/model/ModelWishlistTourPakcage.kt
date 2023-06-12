package com.exploreka.app.retrofit.model

import com.google.gson.annotations.SerializedName

data class ModelWishlistTourPakcage(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("id_wishlist_tour_package")
	val idWishlistTourPackage: Int? = null,

	@field:SerializedName("tour_package")
	val tourPackage: TourPackage? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("id_tour_package")
	val idTourPackage: Int? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)



data class TourPackage(

	@field:SerializedName("name_tour_package")
	val nameTourPackage: String? = null
)
