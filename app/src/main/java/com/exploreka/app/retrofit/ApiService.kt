package com.exploreka.app.retrofit

import com.exploreka.app.data.response.login.LoginResponse
import com.exploreka.app.data.response.main.AttractionsResponse
import com.exploreka.app.data.response.register.RegisterResponse
import com.exploreka.app.retrofit.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @GET("attractions")
    suspend fun getAttractions(): AttractionResponse
//    suspend fun searchAttractionsByName(@Query("name") name: String): AttractionResponse

    @GET("tour_package")
    suspend fun getTourPackage(): TourPackageResponse

    @GET("posts")
    suspend fun getArtikel(): ArtikelResponse

    @GET("wishlist_attraction:/id")
    suspend fun getWishlistAttractions(): List<ModelWishlistAttraction>

    @GET("wishlist_tour_package/:id")
    suspend fun getWishlistTourPackage(): List<ModelWishlistTourPakcage>

    @GET("attractions/{attractionId}")
    suspend fun getAttractionById(@Path("attractionId") attractionId: String): DetailAttractionResponse


}