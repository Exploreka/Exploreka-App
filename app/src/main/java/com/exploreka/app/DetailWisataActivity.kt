package com.exploreka.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialog

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

        btnReview.setOnClickListener {
            val reviewFragment = ReviewFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentReview_container, reviewFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun showBottomSheetDialog(layoutResId: Int) {
        val view = layoutInflater.inflate(layoutResId, findViewById(android.R.id.content), false)

        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
    }
}