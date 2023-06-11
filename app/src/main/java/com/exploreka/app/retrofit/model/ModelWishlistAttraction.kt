package com.exploreka.app.retrofit.model

import com.google.gson.annotations.SerializedName

data class ModelWishlistAttraction(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("attraction")
	val attraction: Attraction? = null,

	@field:SerializedName("id_wishlist_attraction")
	val idWishlistAttraction: Int? = null,

	@field:SerializedName("id_user")
	val idUser: Int? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("id_attraction")
	val idAttraction: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class User(

	@field:SerializedName("fullname_user")
	val fullnameUser: String? = null
)

data class Attraction(

	@field:SerializedName("name_attraction")
	val nameAttraction: String? = null
)
