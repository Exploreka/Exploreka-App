package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.retrofit.ApiClient
import com.exploreka.app.retrofit.ApiService
import com.exploreka.app.retrofit.model.ModelAttraction
import com.exploreka.app.ui.adapter.AttractionAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryTempatIbadahActivity : AppCompatActivity() {

    private lateinit var attractionAdapter: AttractionAdapter
    private lateinit var apiService: ApiService
    private lateinit var rv_category_tempat_ibadah: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_tempat_ibadah)

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            // Tuliskan logika navigasi ke halaman sebelumnya di sini
            onBackPressed() // Contoh menggunakan onBackPressed()
        }

        apiService = ApiClient.getInstance()
        rv_category_tempat_ibadah= findViewById(R.id.rv_category_tempat_ibadah)

        val layoutManager = GridLayoutManager(this, 2)
        rv_category_tempat_ibadah.layoutManager = layoutManager
        fetchDataAttractionTempatIbadah()

    }
    private fun fetchDataAttractionTempatIbadah() {
        // ...

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getAttractionsCat6()
                if (response.status == "Success") {
                    val attractions = response.data
                    attractionAdapter = attractions?.let { AttractionAdapter(it) }!!
                    rv_category_tempat_ibadah.adapter = attractionAdapter

                    // Atur click listener item pada adapter
                    attractionAdapter.setOnItemClickListener(object : AttractionAdapter.OnItemClickListener {
                        override fun onItemClick(attraction: ModelAttraction) {
                            // Tangani acara klik item
                            val intent = Intent(this@CategoryTempatIbadahActivity, DetailWisataActivity::class.java)
                            // Kirim data yang diperlukan ke DetailWisataActivity menggunakan intent
                            intent.putExtra("attractionId", attraction.idAttraction)
                            startActivity(intent)
                        }
                    })

                    rv_category_tempat_ibadah.adapter
                } else {
                    Toast.makeText(this@CategoryTempatIbadahActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@CategoryTempatIbadahActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", e.toString())
            }
        }
    }
}