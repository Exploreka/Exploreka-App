package com.exploreka.app.retrofit.model

import com.google.gson.annotations.SerializedName

data class ModelLoginUser(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
