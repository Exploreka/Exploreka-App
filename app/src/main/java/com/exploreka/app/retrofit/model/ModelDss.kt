package com.exploreka.app.retrofit.model

import com.google.gson.annotations.SerializedName

data class ModelDss(

    @field:SerializedName("predicted_place_id")
    val predictedPlaceId: PredictedPlaceId? = null
)

data class PredictedPlaceId(

    @field:SerializedName("id_city")
    val idCity: Int? = null,

    @field:SerializedName("name_attraction")
    val nameAttraction: String? = null,

    @field:SerializedName("rating_avg_attraction")
    val ratingAvgAttraction: Any? = null,

    @field:SerializedName("id_attraction_cat")
    val idAttractionCat: Int? = null,

    @field:SerializedName("name_attraction_cat")
    val nameAttractionCat: String? = null,

    @field:SerializedName("name_city")
    val nameCity: String? = null,

    @field:SerializedName("id_attraction")
    val idAttraction: Int? = null,

    @field:SerializedName("price_attraction")
    val priceAttraction: Int? = null
)
