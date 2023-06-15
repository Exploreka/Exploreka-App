package com.exploreka.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CategoryBudayaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_budaya)

        data class Category(val categoryId: Int, val name: String)
    }
}