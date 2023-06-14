package com.exploreka.app.retrofit.response

import com.exploreka.app.retrofit.model.ModelRegistrationUser
import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("data")
    val data: List<ModelRegistrationUser>? = null
)