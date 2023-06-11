package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailWisataActivity : AppCompatActivity() {

    private lateinit var btnLocation: Button
    private lateinit var btnOpeningHours: Button
    private lateinit var btnFacility: Button
    private lateinit var btnDescription: Button
    private lateinit var btnReview: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_wisata)

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


    }

    private fun showBottomSheetDialog(layoutResId: Int) {
        val view = layoutInflater.inflate(layoutResId, findViewById(android.R.id.content), false)

        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
    }
}