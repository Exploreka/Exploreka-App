package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.Wisata
import com.exploreka.app.ui.WisataAdapter
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

        val rvWisata: RecyclerView = findViewById(R.id.rv_recomend_destination)
        val wisataList = listOf(
            Wisata(1, "Karimun Jawa","Jepara, Jawa Tengah","4.6","123"),
            Wisata(2, "Kepulauan Togian","Ambon, Maluku","4.9","321"),
            Wisata(3, "Karimun Jawa","Jepara, Jawa Tengah","4.6","123"),
            Wisata(4, "Kepulauan Togian","Ambon, Maluku","4.9","321")
            // Tambahkan kategori lainnya sesuai kebutuhan
        )

        val spanCount = 2 // Jumlah kolom yang diinginkan
        val gridLayoutManager = GridLayoutManager(this, spanCount)
        rvWisata.layoutManager = gridLayoutManager

        val wisataAdapter = WisataAdapter(wisataList)
        rvWisata.adapter = wisataAdapter


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