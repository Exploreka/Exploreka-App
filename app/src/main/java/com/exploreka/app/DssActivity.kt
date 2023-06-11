package com.exploreka.app

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.Category
import com.exploreka.app.ui.CategoryAdapter
import com.exploreka.app.ui.DropdownAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_dss.*
import java.text.DecimalFormat
import java.text.NumberFormat

class DssActivity : AppCompatActivity() {

    private lateinit var dropdownCategory: Button
    private lateinit var dropdownCity: Button
    private lateinit var dropdownAdapter: DropdownAdapter
    private var selectedCategory: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dss)

        dropdownCategory = findViewById(R.id.dropdown_category)
        dropdownCity = findViewById(R.id.dropdown_city)

        dropdownCategory.setOnClickListener {
            showBottomSheetDialog(R.layout.bottom_sheet_category)
        }

        dropdownCity.setOnClickListener {
            showBottomSheetDialog(R.layout.bottom_sheet_city)
        }

        dropdownAdapter = DropdownAdapter(dropdown_rating)
        dropdownAdapter.setupDropdownRating()

        val editBudget = findViewById<TextInputEditText>(R.id.edit_budget)
        editBudget.addTextChangedListener(object : TextWatcher {
            private var current = ""
            private val format: NumberFormat = DecimalFormat("#,###")

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Kosongkan metode ini jika tidak diperlukan
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Kosongkan metode ini jika tidak diperlukan
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    editBudget.removeTextChangedListener(this)

                    val cleanString = s.toString().replace(Regex("[,.]"), "")
                    val parsed = cleanString.toDouble()
                    val formatted = format.format(parsed)

                    current = formatted
                    editBudget.setText(formatted)
                    editBudget.setSelection(formatted.length)

                    editBudget.addTextChangedListener(this)
                }
            }
        })
    }

    private fun showBottomSheetDialog(layoutResId: Int) {
        val view = layoutInflater.inflate(layoutResId, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)

        val rvKategori = view.findViewById<RecyclerView>(R.id.rv_sheet_categoty)
        if (rvKategori != null && layoutResId == R.layout.bottom_sheet_category) {
            val categoryList = listOf(
                Category(1, "Budaya"),
                Category(2, "Taman Hiburan"),
                Category(3, "Cagar Alam"),
                Category(4, "Bahari"),
                Category(5, "Tempat Ibadah"),
                Category(6, "Pusat Belanja"),
                Category(7, "Kategori Lain")
            )
            val kategoriAdapter = CategoryAdapter(categoryList)
            kategoriAdapter.setOnItemClickListener { category ->
                selectedCategory = category.name
                dropdownCategory.text = selectedCategory
                dialog.dismiss()
            }
            rvKategori.adapter = kategoriAdapter
        }

        val chipGroup = view.findViewById<ChipGroup>(R.id.chip_city)
        if (chipGroup != null && layoutResId == R.layout.bottom_sheet_city) {
            chipGroup.setOnCheckedChangeListener { group, checkedId ->
                val selectedChip = view.findViewById<Chip>(checkedId)
                val cityName = selectedChip?.text.toString()
                dropdownCity.text = cityName
                dialog.dismiss()
            }
        }

        dialog.show()
    }
}
