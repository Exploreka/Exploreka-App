package com.exploreka.app.retrofit.model

import com.google.gson.annotations.SerializedName

data class DetailAttractionResponse(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("data")
    val data: ModelAttraction? = null
)
