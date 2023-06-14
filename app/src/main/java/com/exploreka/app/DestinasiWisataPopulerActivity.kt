package com.exploreka.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exploreka.app.data.Wisata
import com.exploreka.app.retrofit.ApiClient
import com.exploreka.app.retrofit.ApiService
import com.exploreka.app.retrofit.model.ModelAttraction
import com.exploreka.app.ui.WisataAdapter
import com.exploreka.app.ui.adapter.AttractionAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DestinasiWisataPopulerActivity : AppCompatActivity() {

    private lateinit var attractionAdapter: AttractionAdapter
    private lateinit var rv_10_destinasi_wisata: RecyclerView
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinasi_wisata_populer)

        apiService = ApiClient.getInstance()

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            // Tuliskan logika navigasi ke halaman sebelumnya di sini
            onBackPressed() // Contoh menggunakan onBackPressed()
        }

        rv_10_destinasi_wisata = findViewById(R.id.rv_10_destinasi_wisata) // Inisialisasi properti rv_10_destinasi_wisata di sini

        val layoutManager = GridLayoutManager(this, 2)
        rv_10_destinasi_wisata.layoutManager = layoutManager
        fetchData()
    }

    private fun fetchData() {
        // ...

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = apiService.getAttractions()
                if (response.status == "Success") {
                    val attractions = response.data
                    attractionAdapter = attractions?.let { AttractionAdapter(it) }!!
                    rv_10_destinasi_wisata.adapter = attractionAdapter

                    // Atur click listener item pada adapter
                    attractionAdapter.setOnItemClickListener(object : AttractionAdapter.OnItemClickListener {
                        override fun onItemClick(attraction: ModelAttraction) {
                            // Tangani acara klik item
                            val intent = Intent(this@DestinasiWisataPopulerActivity, DetailWisataActivity::class.java)
                            // Kirim data yang diperlukan ke DetailWisataActivity menggunakan intent
                            intent.putExtra("attractionId", attraction.idAttraction)
                            startActivity(intent)
                        }
                    })

                    rv_10_destinasi_wisata.adapter
                } else {
                    Toast.makeText(this@DestinasiWisataPopulerActivity, "Failed to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@DestinasiWisataPopulerActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                Log.e("API_FETCH_ERROR", e.toString())
            }
        }
    }

}