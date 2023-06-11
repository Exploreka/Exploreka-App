package  com.exploreka.app.data.response.main

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Attractions(
    @field:SerializedName("id_attraction")
    val id_attraction: Int,

    @field:SerializedName("name_attraction")
    val name_attraction: String,

    @field:SerializedName("desc_attraction")
    val desc_attraction: String,

    @field:SerializedName("photo_attraction")
    val photo_attraction: String,

    @field:SerializedName("coordinate_attraction")
    val coordinate_attraction: String,

    @field:SerializedName("rating_avg_attraction")
    val rating_avg_attraction: String,

    @field:SerializedName("open_hour")
    val open_hour: String,

    @field:SerializedName("close_hour")
    val close_hour: String,

    @field:SerializedName("id_attraction_cat")
    val id_attraction_cat: Int,

    @field:SerializedName("id_city")
    val id_city: Int,

    ) : Parcelable
