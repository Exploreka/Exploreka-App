package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exploreka.app.databinding.ActivityDetailWisataBinding
import com.exploreka.app.retrofit.ApiClient
import com.exploreka.app.retrofit.ApiService
import com.exploreka.app.retrofit.model.ModelAttraction
import com.exploreka.app.ui.adapter.AttractionAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_detail_wisata.*
import kotlinx.android.synthetic.main.bottom_sheet_description.*
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
    private lateinit var attractionAdapter: AttractionAdapter
    private lateinit var attraction: ModelAttraction
    private lateinit var rv_recomend_destinasi_wisata: RecyclerView

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

        btnFacility.setOnClickListener {
            showBottomSheetDialog(R.layout.bottom_sheet_facility)
        }

        btnDescription.setOnClickListener {
            getDataBottomSheetDescription()
        }

        btnReview.setOnClickListener {
            val intent = Intent(this@DetailWisataActivity, ReviewActivity::class.java)
            startActivity(intent)
        }

        val backButton = findViewById<ImageButton>(R.id.btn_back)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val btn360: Button = findViewById(R.id.btn_360)
        btn360.setOnClickListener {
            val intent = Intent(this@DetailWisataActivity, ViewWisataActivity::class.java)
            startActivity(intent)
        }

        rv_recomend_destinasi_wisata = findViewById(R.id.rv_recomend_destination)
        rv_recomend_destinasi_wisata.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)



        getData()
        fetchDataWisata()
    }

    private fun showBottomSheetDialog(layoutResId: Int) {
        val view = layoutInflater.inflate(layoutResId, null)
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
                        tv_description.text = attraction?.descAttraction
                        tv_open_time.text = attraction?.openHour
                        tv_close_time.text = attraction?.closeHour
                        if (attraction != null) {
                            lokasiTextView.text = attraction.city?.nameCity
                            tv_location.text = attraction.city?.nameCity
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

    private fun getDataBottomSheetDescription() {
        val attractionId = intent.getIntExtra("attractionId", 0).toString()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getAttractionById(attractionId)
                if (response.status == "Success") {
                    val attraction = response.data
                    val description = attraction?.descAttraction

                    val bottomSheetDescription = BottomSheetDialog(this@DetailWisataActivity)
                    val view = layoutInflater.inflate(R.layout.bottom_sheet_description, null)
                    bottomSheetDescription.setContentView(view)

                    val tvDescription = view.findViewById<TextView>(R.id.tv_desc_sheetDescription)
                    tvDescription.text = description

                    bottomSheetDescription.show()
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

    private fun fetchDataWisata() {
        // ...

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getAttractions()
                if (response.status == "Success") {
                    val attractions = response.data
                    attractionAdapter = attractions?.let { AttractionAdapter(it) }!!
                    rv_recomend_destinasi_wisata.adapter = attractionAdapter

                    // Atur click listener item pada adapter
                    attractionAdapter.setOnItemClickListener(object : AttractionAdapter.OnItemClickListener {
                        override fun onItemClick(attraction: ModelAttraction) {
                            // Tangani acara klik item
                            val intent = Intent(this@DetailWisataActivity, DetailWisataActivity::class.java)
                            // Kirim data yang diperlukan ke DetailWisataActivity menggunakan intent
                            intent.putExtra("attractionId", attraction.idAttraction)
                            startActivity(intent)
                        }
                    })

                    rv_recomend_destinasi_wisata.adapter
                } else {
                    Toast.makeText(this@DetailWisataActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@DetailWisataActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", e.toString())
            }
        }
    }
}
