package com.exploreka.app.data

import java.io.Serializable

data class Wisata(
    val photo: Int,
    val name: String,
    val location: String,
    val review_star: String,
    val jumlah_ulasan: String
) : Serializable
