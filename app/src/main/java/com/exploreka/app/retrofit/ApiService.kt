package com.exploreka.app.retrofit

import com.exploreka.app.data.response.login.LoginResponse
import com.exploreka.app.data.response.main.AttractionsResponse
import com.exploreka.app.data.response.register.RegisterResponse
import com.exploreka.app.retrofit.model.ModelAttraction
import com.exploreka.app.retrofit.model.ModelTourPackage
import com.exploreka.app.retrofit.model.ModelWishlistAttraction
import com.exploreka.app.retrofit.model.ModelWishlistTourPakcage
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @GET("attractions")
    suspend fun getAttractions(): List<ModelAttraction>

    @GET("tour_package")
    suspend fun getTourPackage(): List<ModelTourPackage>

    @GET("wishlist_attraction:/id")
    suspend fun getWishlistAttractions(): List<ModelWishlistAttraction>

    @GET("wishlist_tour_package/:id")
    suspend fun getWishlistTourPackage(): List<ModelWishlistTourPakcage>



}