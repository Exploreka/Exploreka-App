package com.exploreka.app.retrofit

import com.exploreka.app.retrofit.model.*
import retrofit2.Response
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

    @GET("tour_package/{tourpackageId}")
    suspend fun getTourPackageById(@Path("tourpackageId") tourpackageId: String): DetailTourPackageResponse

    @GET("attractions/cat/1")
    suspend fun getAttractionsCat1(): AttractionResponse

    @GET("attractions/cat/2")
    suspend fun getAttractionsCat2(): AttractionResponse

    @GET("attractions/cat/3")
    suspend fun getAttractionsCat3(): AttractionResponse

    @GET("attractions/cat/4")
    suspend fun getAttractionsCat4(): AttractionResponse

    @GET("attractions/cat/5")
    suspend fun getAttractionsCat5(): AttractionResponse

    @GET("attractions/cat/6")
    suspend fun getAttractionsCat6(): AttractionResponse

    @POST("content-based-recommendation")
    suspend fun searchAttraction(@Body request: ModelInputContentBaseRecommend): Response<ModelResponseContentBaseRecommend>
}