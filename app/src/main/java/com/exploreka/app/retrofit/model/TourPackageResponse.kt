package com.exploreka.app.retrofit.model

import com.google.gson.annotations.SerializedName

data class TourPackageResponse(
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("data")
    val data: List<ModelTourPackage>? = null
)