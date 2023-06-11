package  com.exploreka.app.data.response.main

import com.google.gson.annotations.SerializedName

data class AttractionsResponse(
    @SerializedName("listStory")
    val listStory: List<Attractions>,
    @SerializedName("message")
    val message: String,
    @SerializedName("error")
    val error: Boolean
)
