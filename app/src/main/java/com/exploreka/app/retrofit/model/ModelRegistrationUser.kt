package com.exploreka.app.retrofit.model

import com.google.gson.annotations.SerializedName

data class ModelRegistrationUser(

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("fullname")
    val fullname: String? = null,

    @field:SerializedName("confPassword")
    val confPassword: String? = null,

    @field:SerializedName("email")
    val email: String? = null
)