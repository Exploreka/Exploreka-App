package com.exploreka.app.ui

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.exploreka.app.R

class DropdownAdapter(private val dropdownRating: AutoCompleteTextView) {

    fun setupDropdownRating() {
        val ratingOptions = arrayOf("Bintang 1", "Bintang 2", "Bintang 3", "Bintang 4", "Bintang 5")
        val adapter = ArrayAdapter(dropdownRating.context, R.layout.dropdown_item, ratingOptions)
        dropdownRating.setAdapter(adapter)
        dropdownRating.setOnItemClickListener { _, _, position, _ ->
            val selectedRating = ratingOptions[position]
        }
    }
}