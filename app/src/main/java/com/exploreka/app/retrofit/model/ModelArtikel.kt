package com.exploreka.app.retrofit.model

import com.google.gson.annotations.SerializedName

data class ModelArtikel(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("headline_post")
    val headlinePost: String? = null,

    @field:SerializedName("content_post")
    val contentPost: String? = null,

    @field:SerializedName("id_post")
    val idPost: Int? = null,

    @field:SerializedName("image_post")
    val imagePost: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null

)