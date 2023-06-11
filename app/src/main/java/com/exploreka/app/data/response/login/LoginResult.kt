package com.exploreka.app.data.response.login

import com.google.gson.annotations.SerializedName

data class LoginResult(
    @field:SerializedName("userId")
    val email: String,

    @field:SerializedName("name")
    val password: String,

    @field:SerializedName("token")
    val accessToken: String,
)