package com.exploreka.app

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.exploreka.app.ui.DropdownAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_dss.*

class DssActivity : AppCompatActivity() {

    private lateinit var dropdownCategory: Button
    private lateinit var dropdownCountry: Button
    private lateinit var dropdownAdapter: DropdownAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dss)

        dropdownCategory = findViewById(R.id.dropdown_category)
        dropdownCountry = findViewById(R.id.dropdown_country)

        dropdownCategory.setOnClickListener {
            showBottomSheetDialog(R.layout.bottom_sheet_category)
        }

        dropdownCountry.setOnClickListener {
            showBottomSheetDialog(R.layout.bottom_sheet_country)
        }

        dropdownAdapter = DropdownAdapter(dropdown_rating)
        dropdownAdapter.setupDropdownRating()
    }

    private fun showBottomSheetDialog(layoutResId: Int) {
        val view = layoutInflater.inflate(layoutResId, findViewById(android.R.id.content), false)

        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
    }
}
