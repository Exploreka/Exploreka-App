package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exploreka.app.databinding.ActivityDetailPackageBinding
import com.exploreka.app.retrofit.ApiClient
import com.exploreka.app.retrofit.ApiService
import com.exploreka.app.retrofit.model.ModelAttraction
import com.exploreka.app.retrofit.model.ModelTourPackage
import com.exploreka.app.ui.adapter.AttractionAdapter
import com.exploreka.app.ui.adapter.TourPackageAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_detail_package.*
import kotlinx.android.synthetic.main.activity_detail_wisata.*
import kotlinx.android.synthetic.main.activity_detail_wisata.tv_close_time
import kotlinx.android.synthetic.main.activity_detail_wisata.tv_description
import kotlinx.android.synthetic.main.activity_detail_wisata.tv_open_time
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailPackageActivity : AppCompatActivity() {
    private lateinit var btnLocation: Button
    private lateinit var btnOpeningHours: Button
    private lateinit var btnBuyNow: Button
    private lateinit var btnDescription: Button
    private lateinit var btnReview: Button
    private lateinit var apiService: ApiService
    private lateinit var tourpackageAdapter: TourPackageAdapter
    private lateinit var attraction: ModelAttraction
    private lateinit var rv_recomend_package: RecyclerView

    private val binding: ActivityDetailPackageBinding by lazy {
        ActivityDetailPackageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        apiService = ApiClient.getInstance()

        btnOpeningHours = findViewById(R.id.btn_openingHours)
        btnDescription = findViewById(R.id.btn_description)
        btnReview = findViewById(R.id.btn_review)
        btnBuyNow = findViewById<Button>(R.id.btn_buy_now)


        btnDescription.setOnClickListener {
            getDataBottomSheetDescription()
        }

        btnReview.setOnClickListener {
            val intent = Intent(this@DetailPackageActivity, ReviewActivity::class.java)
            startActivity(intent)
        }


        btnBuyNow.setOnClickListener {
            Toast.makeText(this, "Fitur Pembayaran Belum Tersedia", Toast.LENGTH_SHORT).show()
        }

        rv_recomend_package = findViewById(R.id.rv_recomend_package)
        rv_recomend_package.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)



        val backButton = findViewById<ImageButton>(R.id.btn_back)
        backButton.setOnClickListener {
            onBackPressed()
        }
        getData()
        fetchDataPaket()
    }

    private fun showBottomSheetDialog(layoutResId: Int) {
        val view = layoutInflater.inflate(layoutResId, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
    }

    private fun getData() {
        val tourpackageId = intent.getStringExtra("tourpackageId")

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = tourpackageId?.let { apiService.getTourPackageById(it) }
                if (response != null) {
                    if (response.status == "Success") {
                        val tourpackage = response.data
                        binding.apply {
                            tv_package_touristSpotName.text = tourpackage?.nameTourPackage
                            tv_description.text = tourpackage?.descTourPackage
                            tv_open_time.text = tourpackage?.startHour
                            tv_close_time.text = tourpackage?.endHour
                            tv_package_reviewStar_item.text = tourpackage?.ratingAvgTourPackage.toString()
                            if (tourpackage != null) {
                                Glide.with(this@DetailPackageActivity)
                                    .load(tourpackage.photoTourPackage)
                                    .into(iv_tour_package)
                            }
                        }
                    } else {
                        val errorMessage = "Failed to fetch attraction data."
                        Toast.makeText(this@DetailPackageActivity, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                val errorMessage = "Failed to fetch attraction data. Please check your internet connection."
                Toast.makeText(this@DetailPackageActivity, errorMessage, Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", errorMessage, e)
            }
        }
    }

    private fun getDataBottomSheetDescription() {
        val tourpackageId = intent.getStringExtra("tourpackageId")

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = tourpackageId?.let { apiService.getTourPackageById(it) }
                if (response != null) {
                    if (response.status == "Success") {
                        val tourpackage = response.data
                        val description = tourpackage?.descTourPackage

                        val bottomSheetDescription = BottomSheetDialog(this@DetailPackageActivity)
                        val view = layoutInflater.inflate(R.layout.bottom_sheet_description, null)
                        bottomSheetDescription.setContentView(view)

                        val tvDescription = view.findViewById<TextView>(R.id.tv_desc_sheetDescription)
                        tvDescription.text = description

                        bottomSheetDescription.show()
                    } else {
                        val errorMessage = "Failed to fetch attraction data."
                        Toast.makeText(this@DetailPackageActivity, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                val errorMessage = "Failed to fetch attraction data. Please check your internet connection."
                Toast.makeText(this@DetailPackageActivity, errorMessage, Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", errorMessage, e)
            }
        }
    }

    private fun fetchDataPaket() {
        // ...

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getTourPackage()
                if (response.status == "Success") {
                    val tourpackage = response.data
                    tourpackageAdapter = tourpackage?.let { TourPackageAdapter(it) }!!
                    rv_recomend_package.adapter = tourpackageAdapter

                    // Atur click listener item pada adapter
                    tourpackageAdapter.setOnItemClickListener(object : TourPackageAdapter.OnItemClickListener {
                        override fun onItemClick(tourpackage: ModelTourPackage) {
                            // Tangani acara klik item
                            val intent = Intent(this@DetailPackageActivity, DetailWisataActivity::class.java)
                            // Kirim data yang diperlukan ke DetailWisataActivity menggunakan intent
                            intent.putExtra("tourpackageId", tourpackage.idTourPackage )
                            startActivity(intent)
                        }
                    })

                    rv_recomend_package.adapter
                } else {
                    Toast.makeText(this@DetailPackageActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@DetailPackageActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", e.toString())
            }
        }
    }

}