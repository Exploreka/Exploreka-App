package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.exploreka.app.databinding.ActivityDetailWisataBinding
import com.exploreka.app.retrofit.ApiClient
import com.exploreka.app.retrofit.ApiService
import com.exploreka.app.retrofit.model.ModelAttraction
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_detail_wisata.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class DetailWisataActivity : AppCompatActivity() {

    private lateinit var btnLocation: Button
    private lateinit var btnOpeningHours: Button
    private lateinit var btnFacility: Button
    private lateinit var btnDescription: Button
    private lateinit var btnReview: Button
    private lateinit var apiService: ApiService

    private val binding: ActivityDetailWisataBinding by lazy {
        ActivityDetailWisataBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        apiService = ApiClient.getInstance()

        btnLocation = findViewById(R.id.btn_location)
        btnOpeningHours = findViewById(R.id.btn_openingHours)
        btnFacility = findViewById(R.id.btn_facility)
        btnDescription = findViewById(R.id.btn_description)
        btnReview = findViewById(R.id.btn_review)

        btnLocation.setOnClickListener {
            showBottomSheetDialog(R.layout.bottom_sheet_location)
        }

        btnOpeningHours.setOnClickListener {
            showBottomSheetDialog(R.layout.bottom_sheet_opening_hours)
        }

        btnFacility.setOnClickListener {
            showBottomSheetDialog(R.layout.bottom_sheet_facility)
        }

        btnDescription.setOnClickListener {
            showBottomSheetDialog(R.layout.bottom_sheet_description)
        }

        val btnReview: Button = findViewById(R.id.btn_review)
        btnReview.setOnClickListener {
            val intent = Intent(this@DetailWisataActivity, ReviewActivity::class.java)
            startActivity(intent)
        }

        val backButton = findViewById<ImageButton>(R.id.btn_back)
        backButton.setOnClickListener {
            // Tuliskan logika navigasi ke halaman sebelumnya di sini
            onBackPressed() // Contoh menggunakan onBackPressed()
        }


        val btn360: Button = findViewById(R.id.btn_360)
        btn360.setOnClickListener {
            val intent = Intent(this@DetailWisataActivity, ViewWisataActivity::class.java)
            startActivity(intent)
        }

        getData()

    }

    private fun showBottomSheetDialog(layoutResId: Int) {
        val view = layoutInflater.inflate(layoutResId, findViewById(android.R.id.content), false)

        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
    }

    private fun getData() {
        val attractionId = intent.getIntExtra("attractionId", 0).toString()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getAttractionById(attractionId)
                if (response.status == "Success") {
                    val attraction = response.data
                    binding.apply {
                        tv_touristSpotName.text = attraction?.nameAttraction
                        if (attraction != null) {
                            lokasiTextView.text = attraction.city?.nameCity
                        }
                        tv_reviewStar_item.text = attraction?.ratingAvgAttraction.toString()
                        if (attraction != null) {
                            Glide.with(this@DetailWisataActivity)
                                .load(attraction.photoAttraction)
                                .into(iv_detail_wisata)
                        }
                    }
                } else {
                    val errorMessage = "Failed to fetch attraction data."
                    Toast.makeText(this@DetailWisataActivity, errorMessage, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                val errorMessage = "Failed to fetch attraction data. Please check your internet connection."
                Toast.makeText(this@DetailWisataActivity, errorMessage, Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", errorMessage, e)
            }
        }
    }




}