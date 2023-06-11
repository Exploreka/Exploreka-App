package com.exploreka.app.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : LoginResponse

    @POST("register")
    @FormUrlEncoded
    suspend fun register(
        @Field("fullname") fullname: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confPassword") confPassword: String
    ) : RegisterResponse

    @GET("attractions")
    suspend fun getAttractions(
        @Query("id_attraction") id_attraction: Int,
        @Query("name_attraction") name_attraction: String,
        @Query("desc_attraction") desc_attraction: String,
        @Query("photo_attraction") photo_attraction: String,
        @Query("coordinate_attraction") coordinate_attraction: String,
        @Query("rating_avg_attraction") rating_avg_attraction: String,
        @Query("open_hour") open_hour: String,
        @Query("close_hour") close_hour: String,
        @Query("id_attraction_cat") id_attraction_cat: Int,
        @Query("id_city") id_city: String,
    ): AttractionsResponse



}