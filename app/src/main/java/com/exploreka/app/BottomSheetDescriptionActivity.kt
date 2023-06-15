package com.exploreka.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.exploreka.app.databinding.BottomSheetDescriptionBinding
import com.exploreka.app.retrofit.ApiClient
import com.exploreka.app.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_detail_wisata.*
import kotlinx.android.synthetic.main.bottom_sheet_description.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BottomSheetDescriptionActivity : AppCompatActivity() {

    private val binding: BottomSheetDescriptionBinding by lazy {
        BottomSheetDescriptionBinding.inflate(layoutInflater)
    }
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        apiService = ApiClient.getInstance()
        getDataBottomSheetDescription()
    }
    private fun getDataBottomSheetDescription() {
        val attractionId = intent.getIntExtra("attractionId", 0).toString()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getAttractionById(attractionId)
                if (response.status == "Success") {
                    val attraction = response.data
                    binding.apply {
                        tv_desc_sheetDescription.text = attraction?.descAttraction
                    }
                } else {
                    val errorMessage = "Failed to fetch attraction data."
                    Toast.makeText(this@BottomSheetDescriptionActivity, errorMessage, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                val errorMessage = "Failed to fetch attraction data. Please check your internet connection."
                Toast.makeText(this@BottomSheetDescriptionActivity, errorMessage, Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", errorMessage, e)
            }
        }
    }
}